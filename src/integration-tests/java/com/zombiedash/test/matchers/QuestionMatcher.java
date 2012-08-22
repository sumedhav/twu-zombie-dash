package com.zombiedash.test.matchers;

import com.zombiedash.app.model.Question;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import java.util.Map;

public class QuestionMatcher extends BaseMatcher<Question> {
    private final String text;

    private QuestionMatcher(String text, Map<String, Boolean> options) {
        this.text = text;
    }

    @Override
    public boolean matches(Object item) {
        Question question = (Question) item;
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
