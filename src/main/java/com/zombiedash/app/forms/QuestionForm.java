package com.zombiedash.app.forms;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class QuestionForm {
    private String questionText;
    private List<Option> options;

    private HashMap<String, String> model = new HashMap<String, String>();


    public void setQuestionText(String questionText) {
        this.questionText = questionText;

    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Question createQuestion(UUID taskId) {
        return new Question(UUID.randomUUID(),  questionText, options, taskId);
    }

    public HashMap<String, String> populateModelMapWithFormValues() {
        model.put("text", questionText);
        for(int optionCounter=0; optionCounter<options.size();optionCounter++) {
            model.put("option_"+optionCounter, options.get(optionCounter).getText());
        }
        return model;
    }

    public boolean isValidData() {
        questionText = questionText.trim();

        boolean validDataFlag = this.isEmpty(questionText, "questionTextFieldEmpty");
        validDataFlag &= this.isNumberOfOptionsLessThanTwo();
        for(int optionCounter = 0; optionCounter<options.size(); optionCounter++)   {
            String optionText = options.get(optionCounter).getText().trim();
            validDataFlag  &= this.isEmpty(optionText, "optionTextFieldMissing_"+optionCounter);
        }

        return validDataFlag;
    }

    private boolean isNumberOfOptionsLessThanTwo() {
        if(options.size()<2) {
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
