package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class ConferenceRepositoryIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private ConferenceRepository conferenceRepository;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate.execute("DELETE zombie_conference");
        conferenceRepository = new ConferenceRepository(jdbcTemplate);
    }

    @Test
    @Rollback(true)
    public void shouldSaveConferenceToDatabase() throws Exception {
        Conference conference = new Conference((long)-1,"Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        int numberOfRows = conferenceRepository.saveConference(conference);
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(ConferenceRepository.SQL_CONFERENCE_SELECT,1);
        sqlRowSet.first();
        assertThat(numberOfRows,is(equalTo(1)));
        assertThat(sqlRowSet.getInt(1),is(equalTo(1)));
        assertThat(sqlRowSet.getString(2),is(equalTo("Java Conference")));
        assertThat(sqlRowSet.getString(3),is(equalTo("Java")));
        assertThat(sqlRowSet.getString(4),is(equalTo("for people who really like java")));
        assertThat(sqlRowSet.getString(5),is(equalTo("near you")));
        assertThat(sqlRowSet.getString(6),is(equalTo("2012-08-21")));
        assertThat(sqlRowSet.getString(7),is(equalTo("2012-08-23")));
        assertThat(sqlRowSet.getInt(8),is(equalTo(2)));
    }

    @Test
    @Rollback(true)
    public void shouldRetrieveConferenceFromDatabase() throws Exception {
        long conf_id = 3;
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,conf_id,"Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        Conference actualConference = conferenceRepository.showConference(conf_id);
        assertThat(actualConference.getId(),is(equalTo(conf_id)));
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
        long conf_id1 = 1;
        long conf_id2 = 2;
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,conf_id1,"Java Conference", "Java",
                "for people who really like java", "near you", "2012-08-21", "2012-08-23", 2);
        jdbcTemplate.update(ConferenceRepository.SQL_CONFERENCE_INSERT,conf_id2,"Other Java Conference", "Java",
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
