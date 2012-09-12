package com.zombiedash.app.service;

import com.zombiedash.app.model.AttendeeAnswer;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.AttendeeScoreRepository;
import com.zombiedash.app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ResultService {

    private QuestionRepository questionRepository;
    private AttendeeScoreRepository attendeeScoreRepository;

    @Autowired
    public ResultService(QuestionRepository questionRepository, AttendeeScoreRepository attendeeScoreRepository) {
        this.questionRepository = questionRepository;
        this.attendeeScoreRepository = attendeeScoreRepository;
    }

    public int calculateScore(String username, UUID taskId) {
        List<AttendeeAnswer> answers = attendeeScoreRepository.fetchAnswers(username, taskId);
        List<Question> questions = questionRepository.fetchAllQuestions(taskId.toString());
        int score = 0;
        for (AttendeeAnswer answer : answers) {
            UUID questionId = answer.getQuestionId();
            Question correspondingQuestion = findQuestionById(questionId, questions);
            if (answer.getOptionId().equals(correspondingQuestion.getValidOption()))
                score++;
        }
        attendeeScoreRepository.addCompletedTask(username, taskId, score);
        return score;
    }

    private Question findQuestionById(UUID questionId, List<Question> questions) {

        for (Question question : questions) {
            if (question.getQuestionId().equals(questionId))
                return question;
        }
        return null;
    }


    public List<Question> listQuestions(String taskId) {
        return questionRepository.fetchAllQuestions(taskId);
    }


    public int getAttendeeScore(String username) {
        return attendeeScoreRepository.fetchAttendeeScore(username);
    }

    public boolean isTaskComplete(String username, String taskId) {
        return attendeeScoreRepository.doesTaskExist(username, taskId);
    }
}
