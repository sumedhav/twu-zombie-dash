package com.zombiedash.app.test.matchers;

import com.zombiedash.app.model.User;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class UserMatcher extends BaseMatcher<User> {
    private String username;
    private User actualUser;

    private UserMatcher(String username) {
        this.username = username;
    }

    @Override
    public boolean matches(Object item) {
        actualUser = (User) item;
        return username.equals(actualUser.getUserName());
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("Expected user with username: " + username + ", instead got user with username: " + actualUser.getUserName());
    }

    public static UserMatcher isAUserWithUsername(String username) {
        return new UserMatcher(username);
    }
}
