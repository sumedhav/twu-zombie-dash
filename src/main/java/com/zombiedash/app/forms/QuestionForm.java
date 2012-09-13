package com.zombiedash.app.forms;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestionForm {
  public static final int QUESTION_TEXT_LENGTH = 250;
  private String question_text;
  private List<Option> question_options;
  private UUID questionId;
  private List<String> question_option_texts;
  private List<Integer> question_option_values;
  private HashMap<String, String> model = new HashMap<String, String>();
  private Boolean addAnotherQuestion = false;

  public void setAddAnotherQuestion(Boolean addAnotherQuestion) {
    this.addAnotherQuestion = addAnotherQuestion;
  }

  public Boolean getAddAnotherQuestion() {
    return addAnotherQuestion;
  }

  public void setQuestion_option_texts(List<String> question_option_texts) {
    this.question_option_texts = question_option_texts;
  }

  public void setQuestion_option_values(List<Integer> question_option_values) {
    this.question_option_values = question_option_values;
  }

  public void setQuestion_text(String question_text) {
    this.question_text = question_text;
  }

  public void setQuestion_options(List<Option> question_options) {
    this.question_options = question_options;
  }

  public HashMap<String, String> getModel() {
    return model;
  }

  public Question createQuestion(UUID taskId) {
    return new Question(questionId, question_text, question_options, taskId);
  }

  public HashMap<String, String> populateModelMapWithFormValues(Boolean validDataFlag) {
    if(validDataFlag || question_text!=null) {
      model.put("text", question_text);
    }
    if(validDataFlag || (question_options!=null && question_options.size()<2)) {
      for(int optionCounter=0; optionCounter< question_options.size();optionCounter++) {
        model.put("option_"+ optionCounter, question_options.get(optionCounter).getText());
      }
    }
    return model;
  }

  public boolean isValidData() {
    questionId = UUID.randomUUID();
    question_text = question_text.trim();
    boolean validDataFlag = isNonEmpty(question_text, "questionText_field_empty");
    validDataFlag &= isLessThanMaximumLength(question_text,QUESTION_TEXT_LENGTH,"questionText_exceed_error");
    validDataFlag &= (null != question_option_texts) && (2 <= question_option_texts.size());
    if(validDataFlag) {
      validDataFlag &= ((null != question_option_values) && (0!=question_option_values.size()));
      if(!validDataFlag) {
        model.put("no_option_selected","Select a correct option");
        return validDataFlag;
      }

      initializeQuestionOptions();
      for(int optionCounter = 0; optionCounter< question_options.size(); optionCounter++)   {
        String optionText = question_options.get(optionCounter).getText().trim();
        validDataFlag  &= isNonEmpty(optionText, "optionTextFieldMissing_" + (optionCounter+1));
      }
    } else {
      model.put("less_than_two_options_error", "You must enter atleast two options per question");
    }

    return validDataFlag;
  }

  private void initializeQuestionOptions() {
    question_options = new ArrayList<Option>();
    try {
      for (int i = 0; i < question_option_texts.size(); i++) {
        boolean isChecked = question_option_values.contains(i+1);
        question_options.add(new Option(UUID.randomUUID(), question_option_texts.get(i), isChecked, questionId));
      }
    } catch (ArrayIndexOutOfBoundsException e) {
      question_options.clear();
    }
  }

  private boolean isLessThanMaximumLength(String field, int limit, String errorFieldName) {
    boolean isLessThanMax = field.length() <= limit;
    if (!isLessThanMax) {model.put(errorFieldName, "Trying to exceed the max number (" + limit + ") of characters");}
    return isLessThanMax;
  }

  private boolean isNonEmpty(String field, String fieldMissingErrorName) {
    if(field == null) {
      model.put(fieldMissingErrorName, "You can't leave this field empty.");
      return false;
    }
    boolean isEmpty = field.isEmpty();
    if (isEmpty) {model.put(fieldMissingErrorName,"You can't leave this field empty.");}
    return !isEmpty;
  }
}
