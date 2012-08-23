package com.zombiedash.app.controller;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.service.ResultService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TriviaGameControllerTest {
    @Mock
    QuestionRepository mockQuestionRepository;

    @Mock
    ResultService resultService;

    @Test
    public void shouldForwardToTriviaGamePage() {
        ModelAndView modelAndView = new TriviaGameController(mockQuestionRepository,resultService).showGamePage();
        assertThat(modelAndView.getViewName(), is("triviagamepage"));
    }

    @Test
    public void shouldPopulateModelAndViewWithQuestionsForTheGame(){
        List<Question> expectedQuestions = new ArrayList<Question>();
        List<Option> optionsList1 = new ArrayList<Option>();
        optionsList1.add(new Option(1,"Bangalore", true));
        optionsList1.add(new Option(1,"Paris", false));
        optionsList1.add(new Option(1,"Johannesburg", false));
        optionsList1.add(new Option(1,"London", false));
        Question question = new Question(1, "Where are you?", optionsList1);
        expectedQuestions.add(question);
        List<Option> optionsList2 = new ArrayList<Option>();
        optionsList2.add(new Option(1,"Bangalore", false));
        optionsList2.add(new Option(1,"Paris", false));
        optionsList2.add(new Option(1,"Johannesburg", true));
        optionsList2.add(new Option(1,"London", false));
        Question question2 = new Question(2, "Where are tigers?", optionsList2);
        expectedQuestions.add(question2);
        when(mockQuestionRepository.listAllQuestions()).thenReturn(expectedQuestions);
        ModelAndView modelAndView = new TriviaGameController(mockQuestionRepository,resultService).showGamePage();
        List<Question> actualQuestions = (List<Question>) modelAndView.getModelMap().get("questions");

        assertThat(actualQuestions,sameInstance(expectedQuestions));
    }

    @Test
    public void shouldPopulateModelAndViewScoreForTheGame(){
        when(resultService.calculateScore(new ArrayList<String>())).thenReturn(0);
        ModelAndView modelAndView = new TriviaGameController(mockQuestionRepository,resultService).showResultsPage(new HashMap<String,String>());
        assertThat(modelAndView.getViewName(), is("result"));
    }
}
