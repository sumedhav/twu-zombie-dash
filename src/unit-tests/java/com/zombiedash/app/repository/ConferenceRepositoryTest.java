package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConferenceRepositoryTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
    }

    @Test
  public void shouldNotThrowErrorAndSaveConferenceToDatabase() throws Exception {
    when(jdbcTemplate.queryForInt(ConferenceRepository.SQL_CONFERENCE_NUM_ENTRIES)).thenReturn(0);
    int id = jdbcTemplate.queryForInt(ConferenceRepository.SQL_CONFERENCE_NUM_ENTRIES) + 1;
    when(jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,id,"","","","","","",0)).thenReturn(1);
    ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
    Conference conference = mock(Conference.class);
    when(conference.getDescription()).thenReturn("");
    when(conference.getEndDate()).thenReturn("");
    when(conference.getMaxAttendee()).thenReturn(0);
    when(conference.getName()).thenReturn("");
    when(conference.getStartDate()).thenReturn("");
    when(conference.getTopic()).thenReturn("");
    when(conference.getVenue()).thenReturn("");
    int numberOfRows = conferenceRepository.saveConference(conference);
    assertThat(numberOfRows,is(equalTo(1)));
  }

  @Test
  public void shouldReadConferenceFromDatabase() {
    when(jdbcTemplate.queryForRowSet(ConferenceRepository.SQL_CONFERENCE_SELECT,1))
        .thenAnswer(new Answer<SqlRowSet>() {
          @Override
          public SqlRowSet answer(InvocationOnMock invocation) throws Throwable {
            SqlRowSet sqlRowSet = mock(SqlRowSet.class);
            when(sqlRowSet.getString(anyInt())).thenReturn("java");
            when(sqlRowSet.getString(7)).thenReturn("2012-06-07");
            when(sqlRowSet.getInt(anyInt())).thenReturn(1);
            return sqlRowSet;
          }
        });
    ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
    Conference actualConference = conferenceRepository.showConference(1);
    assertThat(actualConference.getEndDate(),is(equalTo("2012-06-07")));
  }

  @Test
  public void shouldReadAllConferencesFromDatabase(){
    when(jdbcTemplate.queryForList(ConferenceRepository.SQL_CONFERENCE_SELECT_ALL)).thenAnswer(new Answer<List<Map<String, Object>>>() {
      @Override
      public List<Map<String,Object>> answer(InvocationOnMock invocation) throws Throwable {
        List<Map<String,Object>> resultBlock = new ArrayList<Map<String, Object>>();
        HashMap<String,Object> firstResult = new HashMap<String, Object>();
        firstResult.put("id",1);
        firstResult.put("name", "Java");
        firstResult.put("topic","Java");
        firstResult.put("description","Java");
        firstResult.put("venue","here");
        firstResult.put("start_date","2012-06-07");
        firstResult.put("end_date","2012-06-07");
        firstResult.put("max_attendee",1);
        resultBlock.add(firstResult);
        HashMap<String, Object> secondResult = new HashMap<String, Object>();
        secondResult.put("id",2);
        secondResult.put("name", "Java");
        secondResult.put("topic", "Java");
        secondResult.put("description", "Java");
        secondResult.put("venue", "here");
        secondResult.put("start_date","2012-06-07");
        secondResult.put("end_date","2012-06-07");
        secondResult.put("max_attendee", 1);
        resultBlock.add(secondResult);
        return resultBlock;
      }
    });
    ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
    List<Conference> actualConferences = conferenceRepository.showAllConferences();
    for (Conference actualConference : actualConferences) {
      assertThat(actualConference.getEndDate(), is(equalTo("2012-06-07")));
    }
  }
}
