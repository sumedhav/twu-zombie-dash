package com.zombiedash.app.repository;

import com.zombiedash.app.model.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    private QuestionRepository questionRepository;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
        questionRepository = new QuestionRepository(jdbcTemplate);
    }

    @Test
    public void shouldInsertQuestionIntoDatabase(){
        Question question = mock(Question.class);
        UUID questionID = UUID.randomUUID();
        UUID taskID = UUID.randomUUID();
        String INSERT_QUESTION = "INSERT INTO zombie_question values(?,?,?)";

        when(jdbcTemplate.update(anyString(), any(UUID.class), any(Question.class), any(UUID.class))).thenReturn(1);
        when(question.getText()).thenReturn("What is this question?");
        when(question.getTaskId()).thenReturn(taskID);
        when(question.getQuestionId()).thenReturn(questionID);

        questionRepository.insertQuestion(question);
        verify(jdbcTemplate).update(INSERT_QUESTION, questionID, "What is this question?", taskID);
    }

}
