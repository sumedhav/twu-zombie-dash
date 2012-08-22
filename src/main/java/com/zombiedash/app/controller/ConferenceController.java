package com.zombiedash.app.controller;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


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

  @RequestMapping(method = RequestMethod.GET)
  public ModelAndView home() {
    return new ModelAndView("conferencehome");
  }


  @RequestMapping(value = "submit", method = RequestMethod.POST)
  public ModelAndView submit(@RequestParam("conf_name") String conferenceName,
                             @RequestParam("conf_topic") String conferenceTopic,
                             @RequestParam("conf_start_date") String conferenceStartDate,
                             @RequestParam("conf_end_date") String conferenceEndDate,
                             @RequestParam("conf_description") String conferenceDescription,
                             @RequestParam("conf_venue") String conferenceVenue,
                             @RequestParam("conf_max_attendees") String conferenceMaxAttendees,
                             @RequestParam("conf_organiser_name") String conferenceOrganiserName,
                             @RequestParam("conf_organiser_contact_number") String conferenceOrganiserContactNumber,
                             @RequestParam("conf_organiser_email") String conferenceOrganiserEmail) {
    Conference conference =
        new Conference(conferenceName,
            conferenceTopic,
            conferenceDescription,
            conferenceVenue,
            conferenceStartDate,
            conferenceEndDate,
            Integer.parseInt(conferenceMaxAttendees),
            conferenceOrganiserName,
            conferenceOrganiserContactNumber,
            conferenceOrganiserEmail);
    conferenceRepository.saveConference(conference);
    return new ModelAndView("conferencehome");
  }

}
