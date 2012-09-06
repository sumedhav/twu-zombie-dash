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
import java.util.UUID;

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
        UUID questionId1 = UUID.randomUUID();
        optionsList1.add(new Option(questionId1,"Bangalore", true));
        optionsList1.add(new Option(questionId1,"Paris", false));
        optionsList1.add(new Option(questionId1,"Johannesburg", false));
        optionsList1.add(new Option(questionId1,"London", false));
        Question question1 = new Question(questionId1,"Whats the question",optionsList1);

        UUID questionId2 = UUID.randomUUID();
        Question question2= new Question(questionId2,"Whats the second question",optionsList1);
        List<Question> questionList=new ArrayList<Question>();
        questionList.add(question1);

        userAnswers.add("Bangalore");
        userAnswers.add("Delhi");
        when(questionRepository.listAllQuestions()).thenReturn(questionList);
        ResultService resultService = new ResultService(questionRepository);
        assertThat(resultService.calculateScore(userAnswers), is(expectedScore));
    }


}
