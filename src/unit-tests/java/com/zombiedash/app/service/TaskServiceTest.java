package com.zombiedash.app.service;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.TaskRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    private TaskService taskService;
    private QuestionRepository questionRepository;
    private TaskRepository taskRepository;
    private List<Question> questions;
    private UUID randomUUID;

    @Before
    public void setUpTaskService(){
        questionRepository = mock(QuestionRepository.class);
        taskRepository = mock(TaskRepository.class);
        taskService = new TaskService(taskRepository, questionRepository);
        Question question =  mock(Question.class);
        questions = new ArrayList<Question>();
        questions.add(question);
        randomUUID = UUID.randomUUID();
        when(taskRepository.insert(anyString())).thenReturn(randomUUID);

    }

    @Test
    public void shouldInsertTask()  {
        String name = "charles_task_one";
        taskService.insertTask(name, questions);
        verify(taskRepository).insert(name);
    }

    @Test
    public void shouldInsertQuestionsMappedToTask() {
        String name = "sumedha_task_one";
        Question question = mock(Question.class);
        questions.add(question);
        taskService.insertTask(name, questions);
        verify(questionRepository).insertQuestion(randomUUID, questions.get(0));
        verify(questionRepository).insertQuestion(randomUUID, question);
    }
}
