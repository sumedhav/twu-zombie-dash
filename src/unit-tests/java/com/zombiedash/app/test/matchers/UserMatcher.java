package com.zombiedash.app.test.matchers;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class UserMatcher extends BaseMatcher<User> {
    private String username;
    private final String password;
    private final Role role;
    private final String name;
    private final String email;
    private User actualUser;

    private UserMatcher(String username, String password, Role role, String name, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean matches(Object item) {
        actualUser = (User) item;
        return username.equals(actualUser.getUserName()) &&
                password.equals(actualUser.getPassword()) &&
                role == actualUser.getRole() &&
                name.equals(actualUser.getName()) &&
                email.equals(actualUser.getEmail());

    }

    @Override
    public void describeTo(Description description) {
        String expectedUserAttributes = userAttributes(username, password, role, name, email);
        String actualUserAttributes = userAttributes(actualUser.getUserName(), actualUser.getPassword(), actualUser.getRole(), actualUser.getName(), actualUser.getEmail());
        description.appendText("Expected user [" + expectedUserAttributes + "], " + "instead got user with username: [" + actualUserAttributes + "]");
    }

    private String userAttributes(String username, String password, Role role, String name, String email) {
        return StringUtils.join(new String[] {username, password, role.toString(), name, email}, ", ");
    }

    public static UserMatcher isAUserWith(String username, String password, Role role, String name, String email){
        return new UserMatcher(username, password, role, name, email);
    }
}
