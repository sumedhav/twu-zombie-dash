package com.zombiedash.app.controller;

import com.zombiedash.app.forms.QuestionForm;
import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Conference;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.model.Task;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.repository.QuestionRepository;
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
@RequestMapping("/gamedesigner")
public class TaskController {
    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;
    private String conferenceId;
    private ConferenceRepository conferenceRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository, QuestionRepository questionRepository, ConferenceRepository conferenceRepository) {
        this.taskRepository = taskRepository;
        this.questionRepository = questionRepository;
        this.conferenceRepository = conferenceRepository;
    }


    @RequestMapping(value = "home",method=RequestMethod.GET)
    public ModelAndView showHomePage(){
        return ConferenceController.populateWithConferenceList("gamedesignerhome",conferenceRepository);
    }

    @RequestMapping(value = "/conference/{conferenceId}/create/task", method = RequestMethod.GET)
    public ModelAndView showTaskCreationForm(@PathVariable String conferenceId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createtask");
        modelAndView.addObject("conferenceId", conferenceId);
        return modelAndView;
    }
    @RequestMapping(value = "/conference/{conferenceId}", method = RequestMethod.GET)
    public ModelAndView showTasksForConference(@PathVariable String conferenceId) {
        return ConferenceController.showConferenceInformation(conferenceId, "conferencetasks", conferenceRepository,"/zombie/gamedesigner/home");

    }

    @RequestMapping(value = "/conference/{conferenceId}/create/task", method = RequestMethod.POST)
    public ModelAndView createTask(@PathVariable String conferenceId, TaskForm taskForm) {
        this.conferenceId = conferenceId;
        try {
            boolean validDataFlag = taskForm.isValidData();

            HashMap<String, String> model = taskForm.populateModelMapWithFormValues();
            if (validDataFlag) {
                Task task = taskForm.createTask(UUID.fromString(conferenceId));
                UUID taskId = taskRepository.insertTask(task);
                ModelAndView modelAndView = new ModelAndView("redirect:/zombie/gamedesigner/task/" + taskId + "/create/question");
                modelAndView.addObject("taskId", taskId);
                return modelAndView;
            }
            return new ModelAndView("createtask", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("generalerrorpage");
        }
    }

    @RequestMapping(value = "/task/{taskId}/create/question", method = RequestMethod.GET)
    public ModelAndView showQuestionCreationForm(@PathVariable String taskId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createquestion");
        modelAndView.addObject("taskId", taskId);
        return modelAndView;
    }

    @RequestMapping(value = "/task/{taskId}/create/question", method = RequestMethod.POST)
    public ModelAndView createQuestion(@PathVariable String taskId, QuestionForm questionForm) {
        try {
            boolean validDataFlag = questionForm.isValidData();
            HashMap<String, String> model = questionForm.populateModelMapWithFormValues();
            if (validDataFlag) {
                Question question = questionForm.createQuestion(UUID.fromString(taskId));
                questionRepository.insertQuestion(question);
                if(questionForm.getAddAnotherQuestion()){
                  return new ModelAndView("redirect:/zombie/gamedesigner/task/"+ taskId +"/create/question");
                }
                return new ModelAndView("redirect:/zombie/gamedesigner/conference/"+ conferenceId);
            }
            return new ModelAndView("createquestion", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("generalerrorpage");
        }
    }
}