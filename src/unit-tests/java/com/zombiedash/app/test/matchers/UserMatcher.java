package com.zombiedash.app.test.matchers;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class UserMatcher extends BaseMatcher<User> {
    private String username;
    private final Role role;
    private final String name;
    private final String email;
    private User actualUser;

    private UserMatcher(String username, Role role, String name, String email) {
        this.username = username;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean matches(Object item) {
        actualUser = (User) item;
        return username.equals(actualUser.getUserName()) &&
                role == actualUser.getRole() &&
                name.equals(actualUser.getName()) &&
                email.equals(actualUser.getEmail());

    }

    @Override
    public void describeTo(Description description) {
        String expectedUserAttributes = userAttributes(username, role, name, email);
        String actualUserAttributes = userAttributes(actualUser.getUserName(), actualUser.getRole(), actualUser.getName(), actualUser.getEmail());
        description.appendText("Expected user [" + expectedUserAttributes + "], " + "instead got user with username: [" + actualUserAttributes + "]");
    }

    private String userAttributes(String username,Role role, String name, String email) {
        return StringUtils.join(new String[] {username, role.toString(), name, email}, ", ");
    }

    public static UserMatcher isAUserWith(String username, Role role, String name, String email){
        return new UserMatcher(username, role, name, email);
    }
}
