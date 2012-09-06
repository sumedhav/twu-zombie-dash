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
import java.util.UUID;

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
        UUID questionId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        optionsList1.add(new Option(questionId,"Bangalore", true));
        optionsList1.add(new Option(questionId,"Paris", false));
        optionsList1.add(new Option(questionId,"Johannesburg", false));
        optionsList1.add(new Option(questionId,"London", false));
        Question question = new Question(questionId, "Where are you?", optionsList1, taskId);
        expectedQuestions.add(question);

        UUID questionId1 = UUID.randomUUID();
        List<Option> optionsList2 = new ArrayList<Option>();
        optionsList2.add(new Option(questionId1,"Bangalore", false));
        optionsList2.add(new Option(questionId1,"Paris", false));
        optionsList2.add(new Option(questionId1,"Johannesburg", true));
        optionsList2.add(new Option(questionId1,"London", false));
        Question question2 = new Question(questionId1, "Where are tigers?", optionsList2, taskId);
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
