package com.zombiedash.app.service;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.repository.AttendeeRepository;
import com.zombiedash.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class RegistrationService {


    private AttendeeRepository attendeeRepository;
    private UserRepository userRepository;

    @Autowired
    public RegistrationService(AttendeeRepository attendeeRepository, UserRepository userRepository) {
        this.attendeeRepository = attendeeRepository;
        this.userRepository = userRepository;
    }

    public void registerAttendee(Attendee attendee, String password, UUID uuid) {
        if(userRepository.userNameExists(attendee.getUsername())) throw new IllegalArgumentException("userNameAlreadyExists");
        userRepository.insertUser(attendee.getUser(), password);
        attendeeRepository.insertAttendee(attendee, uuid);
    }
}
