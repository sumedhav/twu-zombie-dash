package com.zombiedash.app.service;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest{
    @Mock
    private QuestionRepository questionRepository;

    @Test
    public void shouldVerifyResultForGivenQuestionListAndAnswersList(){
        int expectedScore = 1;
        List<String> userAnswers=new ArrayList<String>();
        List<Option> optionsList1 = new ArrayList<Option>();
        optionsList1.add(new Option(1,"Bangalore", true));
        optionsList1.add(new Option(1,"Paris", false));
        optionsList1.add(new Option(1,"Johannesburg", false));
        optionsList1.add(new Option(1,"London", false));
        Question question1 = new Question(1,"Whats the question",optionsList1);
        Question question2= new Question(1,"Whats the second question",optionsList1);
        List<Question> questionList=new ArrayList<Question>();
        questionList.add(question1);

        userAnswers.add("Bangalore");
        userAnswers.add("Delhi");
        when(questionRepository.listAllQuestions()).thenReturn(questionList);
        ResultService resultService = new ResultService(questionRepository);
        assertThat(resultService.calculateScore(userAnswers), is(expectedScore));
    }


}
