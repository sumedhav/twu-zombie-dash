package com.zombiedash.app.controller;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.ResultRepository;
import com.zombiedash.app.service.ResultService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TriviaGameControllerTest {
   
    @Mock
    ResultService mockResultService;

    @Mock
    ResultRepository mockResultRepository;

    private UUID taskId;
    @Test
    public void shouldForwardToTriviaGamePage() {
        String taskId = UUID.randomUUID().toString();
        ModelAndView modelAndView = new TriviaGameController(mockResultService).showGamePage(taskId);
        when(mockResultService.listQuestions(taskId)).thenReturn(new ArrayList<Question>());
        assertThat(modelAndView.getViewName(), is("triviagamepage"));
    }

    @Test
    public void shouldPopulateModelAndViewWithQuestionsForTheGame(){
        List<Question> expectedQuestions = new ArrayList<Question>();
        List<Option> optionsList1 = new ArrayList<Option>();
        UUID questionId = UUID.randomUUID();
        taskId = UUID.randomUUID();
        optionsList1.add(new Option(UUID.randomUUID(),"Bangalore", true, questionId));
        optionsList1.add(new Option(UUID.randomUUID(),"Paris", false, questionId));
        optionsList1.add(new Option(UUID.randomUUID(),"Johannesburg", false, questionId));
        optionsList1.add(new Option(UUID.randomUUID(),"London", false, questionId));
        Question question = new Question(questionId, "Where are you?", optionsList1, taskId);
        expectedQuestions.add(question);

        UUID questionId1 = UUID.randomUUID();
        List<Option> optionsList2 = new ArrayList<Option>();
        optionsList2.add(new Option(UUID.randomUUID(),"Bangalore", false, questionId1));
        optionsList2.add(new Option(UUID.randomUUID(),"Paris", false, questionId1));
        optionsList2.add(new Option(UUID.randomUUID(),"Johannesburg", true, questionId1));
        optionsList2.add(new Option(UUID.randomUUID(),"London", false, questionId1));
        Question question2 = new Question(questionId1, "Where are tigers?", optionsList2, taskId);
        expectedQuestions.add(question2);
        when(mockResultService.listQuestions(taskId.toString())).thenReturn(expectedQuestions);
        ModelAndView modelAndView = new TriviaGameController(mockResultService).showGamePage(taskId.toString());
        List<Question> actualQuestions = (List<Question>) modelAndView.getModelMap().get("questions");

        assertThat(actualQuestions,sameInstance(expectedQuestions));
    }

    @Ignore
    @Test
    public void shouldRedirectToAttendeeHome() throws Exception {
        TriviaGameController triviaGameController = new TriviaGameController(mockResultService);
        HashMap<String,String> modelMap = mock(HashMap.class);
        Principal principal = mock(Principal.class);
        when(mockResultService.getScoreOfUserSelectedOptions(modelMap,"")).thenReturn(1);
        when(principal.getName()).thenReturn("admin");
        ModelAndView modelAndView = triviaGameController.showResultsPage(modelMap,taskId.toString(),principal);
        assertThat(modelAndView.getViewName(), is("redirect:/zombie/attendee/home"));
    }
}
