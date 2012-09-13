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
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class QuestionFormTest {

  private QuestionForm questionForm;
  private List<Option> options;
  private List<Integer> checkedBoxPosition;
  private List<String> texts;
  private HashMap<String, String> model;

  @Before
  public void setUpQuestionForm() throws Exception {
    questionForm = new QuestionForm();
    Option option1 = mock(Option.class);
    Option option2 = mock(Option.class);
    checkedBoxPosition = mock(List.class);
    texts = mock(List.class);
    options = new ArrayList<Option>();
    options.add(option1);
    options.add(option2);

    questionForm.setQuestion_text("Where is Charles From?");
    questionForm.setQuestion_options(options);
    questionForm.setQuestion_option_texts(texts);
    questionForm.setQuestion_option_values(checkedBoxPosition);
  }

  @Test
  public void shouldCreateQuestion() throws Exception {

    UUID taskId = UUID.randomUUID();

    Question question = questionForm.createQuestion(taskId);

    assertThat(question.getText(), is("Where is Charles From?"));
  }

  @Test
  public void shouldPopulateModelMapWithQuestionFormData() throws Exception {
    Boolean isValidData=true;
    model = questionForm.populateModelMapWithFormValues(isValidData);
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

    assertFalse(questionForm.isValidData());
    assertThat(questionForm.getModel().get("less_than_two_options_error"), is("You must enter atleast two options per question"));
  }

  private void fakeOptions() {
    for(int optionCounter=0; optionCounter<options.size(); optionCounter++) {
      when(options.get(optionCounter).getText()).thenReturn("Sample");
      when(texts.size()).thenReturn(options.size());
      when(texts.get(anyInt())).thenReturn("Sample");
      when(checkedBoxPosition.contains(anyObject())).thenReturn(false);
      when(checkedBoxPosition.contains("0")).thenReturn(true);
    }
  }
}
