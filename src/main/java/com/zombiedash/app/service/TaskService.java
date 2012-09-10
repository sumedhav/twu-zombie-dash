package com.zombiedash.app.service;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class TaskService {

    private TaskRepository taskRepository;
    private QuestionRepository questionRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, QuestionRepository questionRepository) {
        this.taskRepository = taskRepository;
        this.questionRepository = questionRepository;
    }

    public void insertTask(String name, List<Question> questions) {
        UUID taskId = taskRepository.insertTask(name);
        for (Question question : questions) {
            questionRepository.insertQuestion(taskId, question);
        }
    }
}
