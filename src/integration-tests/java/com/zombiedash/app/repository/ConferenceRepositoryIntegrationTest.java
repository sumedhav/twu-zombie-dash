package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class ConferenceRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    @Rollback(true)
    public void shouldSaveConferenceToDatabase() throws Exception {
        Conference conference = new Conference("Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        int numberOfRows = conferenceRepository.saveConference(conference);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(ConferenceRepository.SQL_CONFERENCE_SELECT,"Java Conference");
        sqlRowSet.first();
        assertThat(numberOfRows,is(equalTo(1)));
        assertThat(sqlRowSet.getString(1),is(equalTo("Java Conference")));
        assertThat(sqlRowSet.getString(2),is(equalTo("Java")));
        assertThat(sqlRowSet.getString(3),is(equalTo("for people who really like java")));
        assertThat(sqlRowSet.getString(4),is(equalTo("near you")));
        assertThat(sqlRowSet.getString(5),is(equalTo("2012-08-21")));
        assertThat(sqlRowSet.getString(6),is(equalTo("2012-08-23")));
        assertThat(sqlRowSet.getInt(7),is(equalTo(2)));
    }

    @Test
    @Rollback(true)
    public void shouldRetrieveConferenceFromDatabase() throws Exception {
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,"Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        Conference actualConference = conferenceRepository.showConference("Java Conference");
        assertThat(actualConference.getName(),is(equalTo("Java Conference")));
        assertThat(actualConference.getTopic(),is(equalTo("Java")));
        assertThat(actualConference.getDescription(),is(equalTo("for people who really like java")));
        assertThat(actualConference.getVenue(),is(equalTo("near you")));
        assertThat(actualConference.getStartDate(),is(equalTo("2012-08-21")));
        assertThat(actualConference.getEndDate(),is(equalTo("2012-08-23")));
        assertThat(actualConference.getMaxAttendee(),is(equalTo(2)));
    }

    @Test
    @Rollback(true)
    public void shouldRetrieveAllConferencesFromDatabase() throws Exception{
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,"Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,"Other Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        List<Conference> actualConferences = conferenceRepository.showAllConferences();
        assertThat(actualConferences.get(0).getName(),is(equalTo("Java Conference")));
        assertThat(actualConferences.get(1).getName(),is(equalTo("Other Java Conference")));
        for (Conference actualConference : actualConferences) {
            assertThat(actualConference.getTopic(),is(equalTo("Java")));
            assertThat(actualConference.getDescription(),is(equalTo("for people who really like java")));
            assertThat(actualConference.getVenue(),is(equalTo("near you")));
            assertThat(actualConference.getStartDate(),is(equalTo("2012-08-21")));
            assertThat(actualConference.getEndDate(),is(equalTo("2012-08-23")));
            assertThat(actualConference.getMaxAttendee(),is(equalTo(2)));
        }
    }
}
