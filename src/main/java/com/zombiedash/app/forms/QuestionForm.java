package com.zombiedash.app.forms;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestionForm {
    private String question_text;
    private List<Option> question_options;

    private HashMap<String, String> model = new HashMap<String, String>();


    public void setQuestion_text(String question_text) {
        this.question_text = question_text;

    }

    public void setQuestion_options(List<Option> question_options) {
        this.question_options = question_options;
    }

    public Question createQuestion(UUID taskId) {
        return new Question(UUID.randomUUID(), question_text, question_options, taskId);
    }

    public HashMap<String, String> populateModelMapWithFormValues() {
        model.put("text", question_text);
        for(int optionCounter=0; optionCounter< question_options.size();optionCounter++) {
            model.put("option_"+optionCounter, question_options.get(optionCounter).getText());
        }
        return model;
    }

    public boolean isValidData() {
        question_text = question_text.trim();

        boolean validDataFlag = this.isEmpty(question_text, "questionTextFieldEmpty");
        validDataFlag &= this.isNumberOfOptionsLessThanTwo();
        for(int optionCounter = 0; optionCounter< question_options.size(); optionCounter++)   {
            String optionText = question_options.get(optionCounter).getText().trim();
            validDataFlag  &= this.isEmpty(optionText, "optionTextFieldMissing_"+optionCounter);
        }

        return validDataFlag;
    }

    private boolean isNumberOfOptionsLessThanTwo() {
        if(question_options.size()<2) {
            model.put("lessThanTwoOptionsError", "You must enter more than two options per question");
            return false;
        }
        return true;
    }
    private boolean isEmpty(String field, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"You can't leave this field empty.");
            return false;
        }
        return true;
    }
}
