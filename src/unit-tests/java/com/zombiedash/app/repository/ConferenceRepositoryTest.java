package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.annotation.Rollback;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ConferenceRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
    }

    @Test
    public void shouldNotThrowErrorAndSaveConferenceToDatabase() throws Exception {
        when(jdbcTemplate.update(anyString(), anyObject(),anyString(),anyString(), anyString(),anyString(),anyString(),
                anyString(), anyInt())).thenReturn(1);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        Conference conference = mock(Conference.class);
        UUID uuid = UUID.randomUUID();
        when(conference.getId()).thenReturn(uuid);
        when(conference.getDescription()).thenReturn("");
        when(conference.getEndDate()).thenReturn("");
        when(conference.getMaxAttendee()).thenReturn(0);
        when(conference.getName()).thenReturn("");
        when(conference.getStartDate()).thenReturn("");
        when(conference.getTopic()).thenReturn("");
        when(conference.getVenue()).thenReturn("");
        conferenceRepository.insertConference(conference);
        verify(jdbcTemplate).update("INSERT INTO zombie_conference values (?,?,?,?,?,?,?,?)",uuid, "", "", "", "", "", "", 0);
    }

    @Test
    public void shouldReadConferenceFromDatabase() {
        UUID conferenceId = UUID.randomUUID();
        when(jdbcTemplate.queryForRowSet(ConferenceRepository.SQL_CONFERENCE_SELECT,conferenceId))
                .thenAnswer(new Answer<SqlRowSet>() {
                    @Override
                    public SqlRowSet answer(InvocationOnMock invocation) throws Throwable {
                        SqlRowSet sqlRowSet = mock(SqlRowSet.class);
                        when(sqlRowSet.getString(anyInt())).thenReturn("java");
                        when(sqlRowSet.getString(7)).thenReturn("2012-06-07");
                        when(sqlRowSet.getInt(8)).thenReturn(1);
                        when(sqlRowSet.getLong(1)).thenReturn(1L);
                        return sqlRowSet;
                    }
                });
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        Conference actualConference = conferenceRepository.fetchConference(conferenceId);
        assertThat(actualConference.getEndDate(),is(equalTo("2012-06-07")));
    }


    @Test
    @Rollback(true)
    public void shouldReadAllConferencesFromDatabase(){
        when(jdbcTemplate.queryForList(ConferenceRepository.SQL_CONFERENCE_SELECT_ALL)).thenAnswer(new Answer<List<Map<String, Object>>>() {
            @Override
            public List<Map<String,Object>> answer(InvocationOnMock invocation) throws Throwable {
                List<Map<String,Object>> resultBlock = new ArrayList<Map<String, Object>>();
                resultBlock.add(generateConferenceContent());
                resultBlock.add(generateConferenceContent());
                return resultBlock;
            }

            private HashMap<String, Object> generateConferenceContent() {
                HashMap<String,Object> firstResult = new HashMap<String, Object>();
                firstResult.put("id", UUID.randomUUID());
                firstResult.put("name", "Java");
                firstResult.put("topic","Java");
                firstResult.put("description","Java");
                firstResult.put("venue","here");
                firstResult.put("start_date","2012-06-07");
                firstResult.put("end_date","2012-06-07");
                firstResult.put("max_attendee",1);
                return firstResult;
            }
        });
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        List<Conference> actualConferences = conferenceRepository.fetchAllConferences();
        assertThat(actualConferences.size(),is(2));
    }

    @Test
    public void shouldReturnFalseIfIdIsNotPresent(){
        UUID conferenceId = UUID.randomUUID();
        when(jdbcTemplate.queryForInt(ConferenceRepository.SQL_COUNT_CONFERENCE,conferenceId)).thenReturn(0);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        assertThat(conferenceRepository.isConferencePresent(conferenceId),is(equalTo(false)));
    }

    @Test
    public void shouldReturnTrueIfIdIsPresent(){
        UUID conferenceId = UUID.randomUUID();
        when(jdbcTemplate.queryForInt(ConferenceRepository.SQL_COUNT_CONFERENCE,conferenceId)).thenReturn(1);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        assertThat(conferenceRepository.isConferencePresent(conferenceId),is(equalTo(true)));
    }
}
