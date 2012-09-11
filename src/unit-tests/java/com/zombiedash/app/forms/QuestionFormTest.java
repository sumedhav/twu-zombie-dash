package com.zombiedash.app.forms;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionFormTest {

    private QuestionForm questionForm;
    private List<Option> options;
    private HashMap<String, String> model;

    @Before
    public void setUpQuestionForm() throws Exception {
        questionForm = new QuestionForm();
        Option option1 = mock(Option.class);
        Option option2 = mock(Option.class);
        options = new ArrayList<Option>();
        options.add(option1);
        options.add(option2);

        questionForm.setQuestion_text("Where is Charles From?");
        questionForm.setQuestion_options(options);
    }

    @Test
    public void shouldCreateQuestion() throws Exception {

        UUID taskId = UUID.randomUUID();

        Question question = questionForm.createQuestion(taskId);

        assertThat(question.getText(), is("Where is Charles From?"));
    }

    @Test
    public void shouldPopulateModelMapWithQuestionFormData() throws Exception {
        model = questionForm.populateModelMapWithFormValues();
        assertThat(model.get("text"), is("Where is Charles From?"));
        for(int optionCounter=0; optionCounter<options.size(); optionCounter++) {
            when(options.get(optionCounter).getText()).thenReturn("");
            assertThat(model.get("option_" + optionCounter), isEmptyOrNullString());
        }
    }

    @Test
    public void shouldReturnFalseIfQuestionTextLeftEmpty() throws Exception {
        questionForm.setQuestion_text("");
        fakeOptions();
        assertFalse(questionForm.isValidData());
    }

    @Test
    public void shouldReturnFalseIfQuestionHasLessThanTwoOptions() throws Exception {
        options.removeAll(options);
        options.add(mock(Option.class));
        model = questionForm.populateModelMapWithFormValues();

        fakeOptions();
        assertFalse(questionForm.isValidData());
        assertThat(model.get("lessThanTwoOptionsError"), is("You must enter more than two options per question"));
    }

    private void fakeOptions() {
        for(int optionCounter=0; optionCounter<options.size(); optionCounter++) {
            when(options.get(optionCounter).getText()).thenReturn("Sample");
        }
    }
}
