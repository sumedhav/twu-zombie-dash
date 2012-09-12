package com.zombiedash.app.service;

import com.zombiedash.app.model.AttendeeAnswer;
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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResultServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private AttendeeScoreRepository attendeeScoreRepository;


    @Test
    public void shouldVerifyResultForGivenQuestionListAndAnswersList() {
        UUID userOption = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        List<AttendeeAnswer> answers = new ArrayList<AttendeeAnswer>();
        AttendeeAnswer mockAttendee = mock(AttendeeAnswer.class);
        answers.add(mockAttendee);
        when(mockAttendee.getOptionId()).thenReturn(userOption);
        UUID questionId = UUID.randomUUID();
        when(mockAttendee.getQuestionId()).thenReturn(questionId);
        when(attendeeScoreRepository.fetchAnswers(anyString(), any(UUID.class))).thenReturn(answers);

        int expectedScore = 1;
        List<Question> questionList = new ArrayList<Question>();
        Question question = mock(Question.class);
        when(question.getValidOption()).thenReturn(userOption);
        when(question.getQuestionId()).thenReturn(questionId);
        questionList.add(question);
        when(questionRepository.fetchAllQuestions(anyString())).thenReturn(questionList);

        ResultService resultService = new ResultService(questionRepository, attendeeScoreRepository);
        assertThat(resultService.calculateScore("admin", taskId), is(expectedScore));
    }

}
