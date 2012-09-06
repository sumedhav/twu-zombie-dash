package com.zombiedash.app.test.matchers;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.List;
import java.util.Map;

public class QuestionMatcher extends BaseMatcher<Question> {
    private final String text;
    private Map<String,Boolean> options;


    private QuestionMatcher(String text, Map<String, Boolean> options) {
        this.text = text;
        this.options = options;
    }

    @Override
    public boolean matches(Object item) {
        Question question = (Question) item;
        return validateQuestionText(question) && validateQuestionOptions(question);
    }

    private boolean validateQuestionOptions(Question question) {
        List<Option> questionOptions = question.getOptions();

        if(options.size() != questionOptions.size())
            return false;

        for(Option option : questionOptions) {
            if(!(options.containsKey(option.getText())
                    && options.get(option.getText()).equals(option.isCorrect())))
                return false;
        }

        return true;
    }

    private boolean validateQuestionText(Question question) {
        return question.getText().equals(text);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a question with title " + text);
    }

    public static QuestionMatcher aQuestionWith(String text, Map<String, Boolean> options) {
        return new QuestionMatcher(text, options);
    }


}
