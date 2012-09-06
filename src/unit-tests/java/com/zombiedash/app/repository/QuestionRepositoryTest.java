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
        when(jdbcTemplate.update(anyString(),anyLong(), any(Question.class), anyLong())).thenReturn(1);

        Question question = mock(Question.class);
        when(question.getText()).thenReturn("What is this question?");
        UUID taskID = UUID.randomUUID();
        UUID questionID = questionRepository.insertQuestion(taskID, question);
        String INSERT_QUESTION = "INSERT INTO zombie_question values(?,?,?)";

        verify(jdbcTemplate).update(INSERT_QUESTION, questionID, "What is this question?", taskID);
    }

}
