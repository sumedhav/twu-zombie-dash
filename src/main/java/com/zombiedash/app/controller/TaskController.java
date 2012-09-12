package com.zombiedash.app.controller;

import com.zombiedash.app.forms.QuestionForm;
import com.zombiedash.app.forms.TaskForm;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.model.Task;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class TaskController {
    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;
  private String conferenceId;

  @Autowired
    public TaskController(TaskRepository taskRepository, QuestionRepository questionRepository) {
        this.taskRepository = taskRepository;
        this.questionRepository = questionRepository;
    }

    @RequestMapping(value = "/conference/{conferenceId}/create/task", method = RequestMethod.GET)
    public ModelAndView showTaskCreationForm(@PathVariable String conferenceId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createtask");
        modelAndView.addObject("conferenceId", conferenceId);
        return modelAndView;
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
                ModelAndView modelAndView = new ModelAndView("redirect:/zombie/admin/task/" + taskId + "/create/question");
                modelAndView.addObject("taskId", taskId);
                return modelAndView;
            }
            return new ModelAndView("createtask", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("generalerrorpage");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/task/{taskId}/create/question", method = RequestMethod.GET)
    public ModelAndView showQuestionCreationForm(@PathVariable String taskId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("createquestion");
        modelAndView.addObject("taskId",taskId);
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
                ModelAndView modelAndView = new ModelAndView("redirect:/zombie/admin/conference/view/"+ conferenceId);
                return modelAndView;
            }
            return new ModelAndView("createquestion", "model", model);
        } catch (Exception e) {
            e.printStackTrace();
            ModelAndView modelAndView = new ModelAndView("generalerrorpage");
            return modelAndView;
        }
    }
}