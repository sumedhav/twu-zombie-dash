package com.zombiedash.app.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ResultTest {

    private ArrayList<Option> optionsList1;
    private ArrayList<Option> optionsList2;
    private List<Question> givenQuestions;
    private Map<Question,String> userAnswer;
    private ArrayList<Option> optionsList;

    @Test
    public void shouldVerifyResultForGivenQuestionListAndAnswersList(){
        userAnswer = new HashMap<Question, String>();
        givenQuestions = new ArrayList<Question>();
        optionsList1 = new ArrayList<Option>();
        optionsList1.add(new Option(1,"Bangalore", true));
        optionsList1.add(new Option(1,"Paris", false));
        optionsList1.add(new Option(1,"Johannesburg", false));
        optionsList1.add(new Option(1,"London", false));
        optionsList2 = new ArrayList<Option>();

        optionsList2.add(new Option(2,"Bangalore", false));
        optionsList2.add(new Option(2,"Delhi", true));
        optionsList2.add(new Option(2,"Pune", false));
        optionsList2.add(new Option(2,"Sydney", false));
        addQuestion(1, "Where are you?", optionsList1,"Bangalore");
        addQuestion(2, "Where is RedFort?", optionsList2,"Delhi");

        int expectedScore = 2;

        Result result = new Result(userAnswer, givenQuestions);
        assertThat(result.calculateScore(), is(expectedScore));
    }

    private void addQuestion(int questionId, String questionText, List<Option> options,String userAnswer) {
        Question question = new Question(questionId, questionText, options);
        givenQuestions.add(question);
        this.userAnswer.put(question, userAnswer);
    }
}
