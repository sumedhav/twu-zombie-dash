package com.zombiedash.app.controller;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.model.Organiser;
import com.zombiedash.app.model.Speaker;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/conference")
public class ConferenceController {

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView create() {
        return new ModelAndView("createconference");
    }


    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public ModelAndView submit(@RequestParam("conf_name") String conferenceName,
                               @RequestParam("conf_topic") String conferenceTopic,
                               @RequestParam("conf_start_date") String conferenceStartDate,
                               @RequestParam("conf_end_date") String conferenceEndDate,
                               @RequestParam("conf_description") String conferenceDescription,
                               @RequestParam("conf_venue") String conferenceVenue,
                               @RequestParam("conf_speaker_name") String conferenceSpeakerName,
                               @RequestParam("conf_speaker_domain") String conferenceSpeakerDomain,
                               @RequestParam("conf_speaker_contact_number") String conferenceSpeakerContactNumber,
                               @RequestParam("conf_speaker_email") String conferenceSpeakerEmail,
                               @RequestParam("conf_organiser_name") String conferenceOrganiserName,
                               @RequestParam("conf_organiser_contact_number") String conferenceOrganiserContactNumber,
                               @RequestParam("conf_organiser_email") String conferenceOrganiserEmail,
                               @RequestParam("conf_max_attendees") String conferenceMaxAttendees,
                               ConferenceRepository conferenceRepository) {
        Speaker speaker =
                new Speaker(conferenceSpeakerName,
                        conferenceSpeakerDomain,
                        conferenceSpeakerContactNumber,
                        conferenceSpeakerEmail);
        Organiser organiser =
                new Organiser(conferenceOrganiserName,
                        conferenceOrganiserContactNumber,
                        conferenceOrganiserEmail);
        Conference conference =
                new Conference(conferenceName,
                        conferenceTopic,
                        conferenceDescription,
                        conferenceVenue,
                        conferenceStartDate,
                        conferenceEndDate,
                        Integer.parseInt(conferenceMaxAttendees),
                        speaker,
                        organiser);
        conferenceRepository.setConference(conference);
        return new ModelAndView("home");
    }

}
