package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class ConferenceRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldSaveToDatabase() throws Exception {
        String name = "Java Conference";
        String topic = "Java";
        String description = "for people who really like java";
        String venue = "near you";
        String startDate = "2012-08-21";
        String endDate = "2012-08-23";
        int maxAttendee = 2;
        String organiserName = "Mr. Smiley";
        String organiserContactNumber = "5555-5555";
        String organiserEmail = "smiley@gmail.com";
        Conference conference = new Conference(name,topic,description,venue,startDate,endDate,maxAttendee,organiserName,organiserContactNumber,organiserEmail);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        conferenceRepository.setConference(conference);
        int numberOfRows = conferenceRepository.saveConference();
        assertThat(numberOfRows,is(equalTo(1)));
    }
}
