package com.zombiedash.app.service;

import com.zombiedash.app.repository.ConferenceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RegistrationServiceTest {
    @Mock
    ConferenceRepository conferenceRepository;

    @Test
    public void shouldReturnTrueIfConferenceIdIsInDatabase(){
        when(conferenceRepository.isConferencePresent(anyLong())).thenReturn(true);
        RegistrationService registrationService = new RegistrationService(conferenceRepository);
        boolean result = registrationService.validateConferenceId((long)1);
        assertTrue(result);
    }

}
