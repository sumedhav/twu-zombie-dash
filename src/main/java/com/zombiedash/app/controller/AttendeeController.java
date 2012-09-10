package com.zombiedash.app.controller;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.repository.AttendeeRepository;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("/zombie/attendee/{attendeeId}/home")
public class AttendeeController {

    private ConferenceRepository conferenceRepository;
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeController(ConferenceRepository conferenceRepository, AttendeeRepository attendeeRepository) {
        this.conferenceRepository = conferenceRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView display(@PathVariable UUID attendeeId) {
        ModelAndView modelAndView = new ModelAndView("attendee");
        Attendee attendee = attendeeRepository.findAttendeeById(attendeeId);
        UUID conferenceId = attendee.getConferenceId();
        modelAndView.addObject("attendee", attendee);
        modelAndView.addObject("conference", conferenceRepository.findConferenceById(conferenceId));

        return modelAndView;
    }

}
