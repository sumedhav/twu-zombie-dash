package com.zombiedash.app.controller;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/admin/conference")
public class ConferenceController {
    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceController(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @RequestMapping(value = "createConference", method = RequestMethod.GET)
    public ModelAndView createConference() {
        return new ModelAndView("createconference");
    }

    @RequestMapping(value = "home",method = RequestMethod.GET)
    public ModelAndView home() {
        List<Conference> conferenceList = conferenceRepository.showAllConferences();
        List<String> conferenceNames = new ArrayList<String>();
        for (Conference conference : conferenceList) {
            conferenceNames.add(conference.getName());
        }
        return new ModelAndView("conferencehome","Conferences", conferenceNames);
    }

    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("conf_name") String conferenceName,
                               @RequestParam("conf_topic") String conferenceTopic,
                               @RequestParam("conf_start_date") String conferenceStartDate,
                               @RequestParam("conf_end_date") String conferenceEndDate,
                               @RequestParam("conf_description") String conferenceDescription,
                               @RequestParam("conf_venue") String conferenceVenue,
                               @RequestParam("conf_max_attendees") String conferenceMaxAttendees) {
        Conference conference;
        try {
            conference = new Conference(
                    conferenceName,
                    conferenceTopic,
                    conferenceDescription,
                    conferenceVenue,
                    conferenceStartDate,
                    conferenceEndDate,
                    Integer.parseInt(conferenceMaxAttendees));
        } catch (RuntimeException e) {
            return new ModelAndView("createconference","ErrorString", "ALL FIELDS ARE COMPULSORY");
        }
        conferenceRepository.saveConference(conference);
        return new ModelAndView("conferencehome");
    }

    @RequestMapping(value = "view/{conferenceName}")
    public ModelAndView view(@PathVariable String conferenceName) {
        Conference thisConference = conferenceRepository.showConference(conferenceName);
        return new ModelAndView("conferenceview","Conference", thisConference);
    }

}
