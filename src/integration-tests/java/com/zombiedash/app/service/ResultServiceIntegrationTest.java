package com.zombiedash.app.service;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.AttendeeScoreRepository;
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
public class ResultServiceIntegrationTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AttendeeScoreRepository resultRepository;
    private UUID taskId;

    @Test
    public void shouldVerifyResultForGivenQuestionListAndAnswersList(){
        int expectedScore = 1;
        List<String> userAnswers=new ArrayList<String>();
        List<Question> questionList = createQuestionList();
        userAnswers.add("Bangalore");
        userAnswers.add("Delhi");
        when(questionRepository.fetchAllQuestions(taskId.toString())).thenReturn(questionList);
        ResultService resultService = new ResultService(questionRepository,resultRepository);
        assertThat(resultService.calculateScore(userAnswers,taskId.toString()), is(expectedScore));
    }

    private List<Question> createQuestionList() {

        taskId = UUID.randomUUID();

        List<Question> questionList=new ArrayList<Question>();

        questionList.add(createQuestion("Whats the question", taskId));
        questionList.add(createQuestion("Whats the second question", taskId));

        return questionList;
    }

    private Question createQuestion(String name, UUID taskId){
        UUID questionId = UUID.randomUUID();
        List<Option> optionList = createOptionList(questionId);
        Question question = new Question(questionId, name, optionList, taskId);
        return question;
    }


    private List<Option> createOptionList(UUID questionId) {
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option(UUID.randomUUID(),"Bangalore", true, questionId));
        optionList.add(new Option(UUID.randomUUID(),"Paris", false, questionId));
        optionList.add(new Option(UUID.randomUUID(),"Johannesburg", false, questionId));
        optionList.add(new Option(UUID.randomUUID(),"London", false, questionId));
        return optionList;
    }


}
