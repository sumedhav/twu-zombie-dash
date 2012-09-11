package com.zombiedash.app.controller;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.forms.ConferenceForm;
import com.zombiedash.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/admin/conference")
public class ConferenceController {
    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceController(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView showConferenceCreationForm() {
        ModelAndView modelAndView = new ModelAndView("createconference");
        return modelAndView;
    }

    @RequestMapping(value = "create" ,method = RequestMethod.POST)
    public ModelAndView createConference(ConferenceForm conferenceForm) {
        try {
            boolean validDataFlag = conferenceForm.isValidData();

            HashMap<String, String> model = conferenceForm.populateModelMapWithFormValues();
            if (validDataFlag) {
                Conference conference = conferenceForm.createConference();
                conferenceRepository.insertConference(conference);
                return new ModelAndView("redirect:/zombie/admin/conference/list","model", model);
            }
            return new ModelAndView("createconference","model", model);
            } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("generalerrorpage");
            modelAndView.addObject("errorMessage",e.getMessage());
            modelAndView.addObject("urlToReturnTo","/zombie/admin/conference/list");
            modelAndView.addObject("returnToPrevPageMessage","Go back to conference home page");
            return modelAndView;
        }
    }


    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView home() {
        List<Conference> conferenceList = conferenceRepository.fetchAllConferences();
        ModelAndView modelAndView = new ModelAndView("conferencehome");
        if(conferenceList.isEmpty())
            modelAndView.addObject("emptyConferenceListMessage","No existing conferences !!");
        modelAndView.addObject("Conferences", conferenceList);
        return modelAndView;
    }

    @RequestMapping(value = "view/{conferenceId}")
    public ModelAndView view(@PathVariable String conferenceId) {
        try{
            Conference thisConference = conferenceRepository.fetchConference(UUID.fromString(conferenceId));
            return new ModelAndView("conferenceview","Conference", thisConference);
        } catch (Exception e) {
            ModelAndView modelAndView = new ModelAndView("generalerrorpage");
            modelAndView.addObject("urlToReturnTo","/zombie/admin/conference/list");
            modelAndView.addObject("returnToPrevPageMessage","Go back to conference home page");
            return modelAndView;
        }
    }

}
