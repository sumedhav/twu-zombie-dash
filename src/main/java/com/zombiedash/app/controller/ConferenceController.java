package com.zombiedash.app.controller;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.validator.ConferenceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
                             @RequestParam("conf_description") String conferenceDescription,
                             @RequestParam("conf_venue") String conferenceVenue,
                             @RequestParam("conf_start_date") String conferenceStartDate,
                             @RequestParam("conf_end_date") String conferenceEndDate,
                             @RequestParam("conf_max_attendees") String conferenceMaxAttendees) {

    Map<String,String> model = new HashMap<String, String>();
    model.put("name",conferenceName);
    model.put("topic",conferenceTopic);
    model.put("startDate",conferenceStartDate);
    model.put("endDate",conferenceEndDate);
    model.put("description",conferenceDescription);
    model.put("venue",conferenceVenue);
    model.put("maxAttendees", conferenceMaxAttendees);

    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean validDataFlag = conferenceValidator.isDataValid(model);

    if (validDataFlag) {
      Conference conference = new Conference(conferenceName, conferenceTopic, conferenceDescription,
          conferenceVenue, conferenceStartDate, conferenceEndDate, Integer.parseInt(conferenceMaxAttendees));
      conferenceRepository.saveConference(conference);
      return home();
    } else {
      return new ModelAndView("createconference", "model", model);
    }


  }



  @RequestMapping(value = "view/{conferenceName}")
  public ModelAndView view(@PathVariable String conferenceName) {
    Conference thisConference = conferenceRepository.showConference(conferenceName);
    return new ModelAndView("conferenceview","Conference", thisConference);
  }

}
