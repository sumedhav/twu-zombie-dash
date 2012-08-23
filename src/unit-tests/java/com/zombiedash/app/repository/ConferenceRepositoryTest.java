package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
  @Test
  public void shouldNotThrowErrorAndSaveConferenceToDatabase() throws Exception {
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    when(jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT, "","","","","","",0,"","","")).thenReturn(1);
    ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
    Conference conference = mock(Conference.class);
    when(conference.getDescription()).thenReturn("");
    when(conference.getEndDate()).thenReturn("");
    when(conference.getMaxAttendee()).thenReturn(0);
    when(conference.getName()).thenReturn("");
    when(conference.getOrganiserContactNumber()).thenReturn("");
    when(conference.getOrganiserEmail()).thenReturn("");
    when(conference.getOrganiserName()).thenReturn("");
    when(conference.getStartDate()).thenReturn("");
    when(conference.getTopic()).thenReturn("");
    when(conference.getVenue()).thenReturn("");
    int numberOfRows = conferenceRepository.saveConference(conference);
    assertThat(numberOfRows,is(equalTo(1)));
  }

  @Test
  public void shouldReadConferenceFromDatabase() {

    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    when(jdbcTemplate.queryForRowSet(ConferenceRepository.SQL_CONFERENCE_SELECT,"java"))
        .thenAnswer(new Answer<SqlRowSet>() {
          @Override
          public SqlRowSet answer(InvocationOnMock invocation) throws Throwable {
            SqlRowSet sqlRowSet = mock(SqlRowSet.class);
            when(sqlRowSet.getString(anyInt())).thenReturn("java");
            when(sqlRowSet.getString(6)).thenReturn("2012-06-07");
            when(sqlRowSet.getInt(anyInt())).thenReturn(0);
            return sqlRowSet;
          }
        });
    ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
    Conference actualConference = conferenceRepository.showConference("java");
    assertThat(actualConference.getEndDate(),is(equalTo("2012-06-07")));
  }

  @Test
  public void shouldReadAllConferencesFromDatabase(){
    JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
    when(jdbcTemplate.queryForList(ConferenceRepository.SQL_CONFERENCE_SELECT_ALL)).thenAnswer(new Answer<List<Map<String, Object>>>() {
      @Override
      public List<Map<String,Object>> answer(InvocationOnMock invocation) throws Throwable {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<Map<String,Object>> resultBlock = new ArrayList<Map<String, Object>>();
        HashMap<String,Object> firstResult = new HashMap<String, Object>();
        firstResult.put("name", "");
        firstResult.put("topic","");
        firstResult.put("description","");
        firstResult.put("venue","");
        firstResult.put("start_date", dateFormat.parse("2012-06-07"));
        firstResult.put("end_date", dateFormat.parse("2012-06-07"));
        firstResult.put("max_attendee",0);
        firstResult.put("organiser_name","");
        firstResult.put("organiser_contact_no","");
        firstResult.put("organiser_email","");
        resultBlock.add(firstResult);
        HashMap<String, Object> secondResult = new HashMap<String, Object>();
        secondResult.put("name", "");
        secondResult.put("topic", "");
        secondResult.put("description", "");
        secondResult.put("venue", "");
        secondResult.put("start_date", dateFormat.parse("2012-06-07"));
        secondResult.put("end_date", dateFormat.parse("2012-06-07"));
        secondResult.put("max_attendee", 0);
        secondResult.put("organiser_name", "");
        secondResult.put("organiser_contact_no", "");
        secondResult.put("organiser_email", "");
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
