package com.zombiedash.app.service;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.User;
import com.zombiedash.app.repository.AttendeeRepository;
import com.zombiedash.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Mock
    private AttendeeRepository attendeeRepository;
    @Mock
    private UserRepository userRepository;
    private RegistrationService registrationService;

    @Before
    public void setUp() throws Exception {
        registrationService = new RegistrationService(attendeeRepository, userRepository);
    }

    @Test
    public void shouldCreateAttendee() throws Exception {
        Attendee attendee = mock(Attendee.class);
        UUID uuid = UUID.randomUUID();
        User user = mock(User.class);
        when(attendee.getUser()).thenReturn(user);

        registrationService.registerAttendee(attendee, "password1", uuid);

        verify(attendeeRepository).insertAttendee(attendee, uuid);
        verify(userRepository).insertUser(user, "password1");
    }

    @Test
    public void shouldThrowExceptionIfUsernameAlreadyExists() {
        Attendee attendee = mock(Attendee.class);
        when(attendee.getUsername()).thenReturn("alreadyExistingUser");
        when(userRepository.userNameExists("alreadyExistingUser")).thenReturn(true);
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("userNameAlreadyExists");
        registrationService.registerAttendee(attendee, "password1", UUID.randomUUID());
    }
}
