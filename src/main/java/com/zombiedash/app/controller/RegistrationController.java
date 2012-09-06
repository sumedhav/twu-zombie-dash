package com.zombiedash.app.controller;

import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class RegistrationController {

    private ConferenceRepository conferenceRepository;

    @Autowired
    public RegistrationController(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @RequestMapping(value = "/register/{confId}", method= GET)
    public ModelAndView openRegistrationPage(@PathVariable("confId") long confId) {
        if (conferenceRepository.isConferencePresent(confId)) {
            Map<String,Long> conferenceMap = new HashMap<String,Long>();
            conferenceMap.put("confId",confId);
            return new ModelAndView("attendeeregistration","model",conferenceMap);
        } else {
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/register/{confId}", method = POST)
    public ModelAndView submitRegistrationPage(@PathVariable("confId") long confId) {
        return new ModelAndView("registrationconfirmed");
    }

}
