package com.zombiedash.app.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class QuestionTest {
    @Test
    public void shouldReturnCorrectOption(){
        UUID questionId = UUID.randomUUID();
        System.out.println(questionId);
        List<Option> anOptionList = new ArrayList<Option>();
        anOptionList.add(new Option(questionId, "Bangalore", true));
        anOptionList.add(new Option(questionId, "Paris", false));
        anOptionList.add(new Option(questionId, "Johannesburg", false));

        String expectedCorrectOption = "Bangalore";
        Question aQuestion = new Question(questionId, "Where are you?", anOptionList);

        assertThat(aQuestion.getValidOption(), equalTo(expectedCorrectOption));
    }
}
