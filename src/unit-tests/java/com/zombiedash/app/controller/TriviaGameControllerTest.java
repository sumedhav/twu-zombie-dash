package com.zombiedash.app.controller;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TriviaGameControllerTest {
    @Mock
    QuestionRepository mockQuestionRepository;

    @Test
    public void shouldForwardToTriviaGamePage() {
        ModelAndView modelAndView = new TriviaGameController(mockQuestionRepository).showGamePage();
        assertThat(modelAndView.getViewName(), is("triviagamepage"));
    }

    @Test
    public void shouldPopulateModelAndViewWithQuestionsForTheGame(){
        List<Question> expectedQuestions = new ArrayList<Question>();
        when(mockQuestionRepository.listAllQuestions()).thenReturn(expectedQuestions);

        ModelAndView modelAndView = new TriviaGameController(mockQuestionRepository).showGamePage();
        List<Question> actualQuestions = (List<Question>) modelAndView.getModelMap().get("questions");

        assertThat(actualQuestions,sameInstance(expectedQuestions));
    }
}
