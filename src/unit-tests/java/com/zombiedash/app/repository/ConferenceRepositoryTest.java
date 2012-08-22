package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

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
}
