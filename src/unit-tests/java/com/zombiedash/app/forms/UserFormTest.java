package com.zombiedash.app.forms;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import com.zombiedash.app.test.matchers.UserMatcher;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(Role.class)
public class UserFormTest {

    private UserForm userFormFactory(String username, String role, String name, String email, String password) {
        UserForm userForm = new UserForm();
        userForm.setEmail(email);
        userForm.setFullName(name);
        userForm.setPassword(password);
        userForm.setRole(role);
        userForm.setUserName(username);
        return userForm;
    }

    @Test
    public void shouldReturnEmptyFieldErrorWhenUserNameIsEmpty() {
        UserForm userForm = userFormFactory("", "", "", "", "");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("usernameFieldEmpty")));
    }

    @Test
    public void shouldReturnInvalidUserNameWhenUserNameHasWhitespaces() {
        UserForm userForm = userFormFactory("user 123","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameIsBelowLowerLimit() throws Exception {
        UserForm userForm = userFormFactory("user","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameIsAboveUpperLimit() throws Exception {
        UserForm userForm = userFormFactory("qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameHasSpecialCharacters() throws Exception {
        UserForm userForm = userFormFactory("qwertyuioplkjhgfdsazxcvbnmqwertyu@#","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));

    }

    @Test
    public void shouldReturnEmptyFieldErrorWhenPasswordIsEmpty() throws Exception {
        UserForm userForm = userFormFactory("user123","role","name","email@email.com", "");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("passwordFieldEmpty")));
    }

    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordIsBelowLowerLimit() throws Exception {
        UserForm userForm = userFormFactory("username","role","name","email@email.com", "passw");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));
    }


    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordIsAboveUpperLimit() throws Exception {
        UserForm userForm = userFormFactory("username","role","name","email@email.com", "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));
    }

    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordHasSpecialCharacters() throws Exception {
        UserForm userForm = userFormFactory("username","role","name","email@email.com", "password134234*&^fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));

    }

    @Test
    public void shouldReturnEmptyFieldErrorWhenNameIsEmpty() throws Exception {
        UserForm userForm = userFormFactory("user123","role","","email@email.com", "password123");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("nameFieldEmpty")));
    }

    @Test
    public void shouldReturnInvalidNameErrorWhenNameExceedsUpperLimit() {
        UserForm userForm = userFormFactory("username","role","qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidName")));
    }
    @Test
    public void shouldReturnInvalidNameErrorWhenNameIsOnlyWhiteSpaces() {
        UserForm userForm = userFormFactory("username","role","         ","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("nameFieldEmpty")));
    }

    @Test
    public void shouldReturnEmptyFieldErrorWhenEmailIsEmpty() throws Exception {
        UserForm userForm = userFormFactory("user123","role","name","", "password123");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("emailFieldEmpty")));
    }

    @Test
    public void shouldValidateEmail() throws Exception {
        UserForm userForm = userFormFactory("username","role","name","emailemail.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidEmail")));
    }

    @Test
    public void shouldReturnNoErrorsWhenAllCredentialsAreValid() {
        UserForm userForm = userFormFactory("user123","role","name","email@email.com", "password134234fsd");
        userForm.validate();
        assertThat(userForm.hasErrors(),is(false));
    }

    @Test
    public void shouldPopulateModelMapWithFormValues() throws Exception {
        UserForm userForm = userFormFactory("user123","role","name","email@email.com", "password134234fsd");
        ModelMap modelMap = userForm.populateFormValuesIntoMap();
        String username =(String) modelMap.get("username");
        assertThat(username,is("user123"));
        assertThat((String)modelMap.get("role"),is("role"));
        assertThat((String)modelMap.get("fullName"),is("name"));
        assertThat((String)modelMap.get("email"),is("email@email.com"));
        assertThat((String)modelMap.get("password"),is("password134234fsd"));
    }

    @Test
    public void shouldCreateAUser() throws Exception {
        UserForm userForm = userFormFactory("user123","Game_Designer","name","email@email.com", "password134234fsd");
        User user = userForm.createUser();
        assertThat(user, UserMatcher.isAUserWith("user123", Role.GAME_DESIGNER, "name", "email@email.com"));
    }
}
