package com.zombiedash.app.unit.repository;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ConferenceRepositoryTest {
    @Test
    public void shouldNotThrowErrorAndSaveConferenceToDatabase() throws Exception {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.update(anyString(), any(Object[].class))).thenReturn(1);
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        Conference conference = mock(Conference.class);
        when(conference.getDescription()).thenReturn("");
        when(conference.getEndDate()).thenReturn("");
        when(conference.getMaxAttendee()).thenReturn(1);
        when(conference.getName()).thenReturn("");
        when(conference.getOrganiserContactNumber()).thenReturn("");
        when(conference.getOrganiserEmail()).thenReturn("");
        when(conference.getOrganiserName()).thenReturn("");
        when(conference.getStartDate()).thenReturn("");
        when(conference.getTopic()).thenReturn("");
        when(conference.getVenue()).thenReturn("");

        conferenceRepository.setConference(conference);
        int numberOfRows = conferenceRepository.saveConference();
        assertThat(numberOfRows,is(equalTo(1)));
    }
}
