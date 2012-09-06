package com.zombiedash.app.service;

import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private ConferenceRepository conferenceRepository;

    @Autowired
    public RegistrationService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public boolean validateConferenceId(long confId) {
        return conferenceRepository.isConferencePresent(confId);
    }
}
