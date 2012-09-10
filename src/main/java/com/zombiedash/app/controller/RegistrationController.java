package com.zombiedash.app.controller;

import com.zombiedash.app.error.ValidationMessagesMap;
import com.zombiedash.app.forms.RegistrationForm;
import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.repository.AttendeeRepository;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/")
public class RegistrationController {

    private ConferenceRepository conferenceRepository;
    private ValidationMessagesMap validationMessagesMap;
    private AttendeeRepository attendeeRepository;

    @Autowired
    public RegistrationController(ConferenceRepository conferenceRepository, AttendeeRepository attendeeRepository, ValidationMessagesMap validationMessagesMap) {
        this.conferenceRepository = conferenceRepository;
        this.attendeeRepository = attendeeRepository;
        this.validationMessagesMap = validationMessagesMap;
    }

    @RequestMapping(value = "/register/{confId}", method= GET)
    public ModelAndView openRegistrationPage(@PathVariable("confId") String confId) {
        UUID uuid;
        try {
            uuid = UUID.fromString(confId);
        } catch (Exception e) {
            return new ModelAndView("404");
        }
        if (conferenceRepository.isConferencePresent(uuid)) {
            Map<String,UUID> conferenceMap = new HashMap<String,UUID>();
            conferenceMap.put("confId",uuid);
            return new ModelAndView("attendeeregistration","model",conferenceMap);
        } else {
            return new ModelAndView("404");
        }
    }

    @RequestMapping(value = "/register/{conferenceId}", method = POST)
    public ModelAndView submitRegistrationPage(@PathVariable("conferenceId") String conferenceId, RegistrationForm registrationForm) {
        ModelAndView modelAndView;
        registrationForm.validate();
        if(registrationForm.hasErrors()){
            modelAndView = new ModelAndView("attendeeregistration");
            populateErrorsInModelAndView(modelAndView, registrationForm.getErrors());
            modelAndView.addObject("model", registrationForm.populateFormValuesToModelMap());
            return modelAndView;
        }else{
            try{
                Attendee attendee = registrationForm.createAttendee();
                attendeeRepository.saveAttendee(attendee, registrationForm.getPassword(), UUID.fromString(conferenceId));
                modelAndView = new ModelAndView("registrationconfirmed");
                return modelAndView;
            }catch (Exception exception){
                modelAndView = new ModelAndView("generalerrorpage");
                modelAndView.addObject("urlToReturnTo","/zombie/registration/"+conferenceId);
                modelAndView.addObject("returnToPrevPageMessage","Go Back To Registration Page to try again");
                return modelAndView;
            }
        }
     }

    private void populateErrorsInModelAndView(ModelAndView modelAndView, List<String> errorCodes) {
        for (String errorCode : errorCodes) {
            modelAndView.addObject(errorCode,validationMessagesMap.getMessageFor(errorCode));
        }
    }

}
