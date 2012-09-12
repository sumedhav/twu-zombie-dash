package com.zombiedash.app.repository;

import com.zombiedash.app.model.Attendee;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttendeeRepositoryTest {
    @Test
    public void shouldSaveAnAttendee() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        AttendeeRepository attendeeRepository = new AttendeeRepository(jdbcTemplate);
        Attendee attendee = mock(Attendee.class);
        boolean isAttendeeSaved = attendeeRepository.insertAttendeeInfo(attendee, UUID.randomUUID());
        assertTrue(isAttendeeSaved);
    }

    @Test
    public void shouldRetrieveAttendeeByUserNameAndConferenceName() {
        JdbcTemplate jdbcTemplate = mock(JdbcTemplate.class);
        when(jdbcTemplate.query(anyString(), Matchers.any(Object[].class), Matchers.any(RowMapper.class)))
                .thenAnswer(new Answer<List<Attendee>>() {
                    @Override
                    public List<Attendee> answer(InvocationOnMock invocation) throws Throwable {
                        Attendee attendee = mock(Attendee.class);
                        when(attendee.getUsername()).thenReturn("username");
                        ArrayList<Attendee> attendeeArrayList = new ArrayList<Attendee>();
                        attendeeArrayList.add(attendee);
                        return attendeeArrayList;
                    }
                });
        AttendeeRepository attendeeRepository = new AttendeeRepository(jdbcTemplate);
        Attendee attendee = attendeeRepository.fetchAttendee("username", "conference");
        assertThat(attendee.getUsername(), is(equalTo("username")));
    }
}
