package com.zombiedash.app.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class QuestionTest {
    @Test
    public void shouldReturnCorrectOption(){
        List<Option> anOptionList = new ArrayList<Option>();
        anOptionList.add(new Option(1, "Bangalore", true));
        anOptionList.add(new Option(2, "Paris", false));
        anOptionList.add(new Option(3, "Johannesburg", false));

        String expectedCorrectOption = "Bangalore";
        Question aQuestion = new Question(3, "Where are you?", anOptionList);

        assertThat(aQuestion.getValidOption(), equalTo(expectedCorrectOption));
    }
}
