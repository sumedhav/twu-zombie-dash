package com.zombiedash.app.service;

import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.QuestionRepository;
import com.zombiedash.app.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ResultService {

    private QuestionRepository questionRepository;
    private int POINTS_FOR_CORRECT_ANSWER = 1;
    private ResultRepository resultRepository;

    @Autowired
    public ResultService(QuestionRepository questionRepository, ResultRepository resultRepository) {
        this.questionRepository = questionRepository;
        this.resultRepository = resultRepository;
    }

    public int calculateScore(List<String> userAnswers, String taskId) {
        int score = 0, currentAnswer = 0;
        for (Question question : listQuestions(taskId)) {
            if (question.getValidOption().equals(userAnswers.get(currentAnswer))) {

                score += POINTS_FOR_CORRECT_ANSWER;
            }
            currentAnswer++;
        }
        return score;
    }

    public int getScoreOfUserSelectedOptions(Map<String, String> params, String taskId) {
        int noOfQuestionsInRepository = listQuestions(taskId).size();
        List<String> userAnswers = new ArrayList<String>();
        for (int questionId = 1; questionId <= noOfQuestionsInRepository; questionId++) {
            userAnswers.add(params.get("question_" + questionId));
        }
        return calculateScore(userAnswers, taskId);
    }

    public List<Question> listQuestions(String taskId) {
        return questionRepository.fetchAllQuestions(taskId);
    }

    public void addCompletedTask(String username, UUID taskId, int score) {
        resultRepository.addCompletedTask(username, taskId,score);
    }

    public int getAttendeeScore(String username) {
        return resultRepository.getAttendeeScore(username);
    }
}
