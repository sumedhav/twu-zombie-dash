package com.zombiedash.app.forms;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserFormTest {

    @Test
    public void shouldReturnAnErrorMessageIfAnyFieldIsEmpty () throws Exception {
        UserForm userForm = new UserForm("",null,"name","email@email.com",null);
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("allFieldsAreMandatory") ));
    }

    @Test
    public void shouldReturnInvalidUserNameWhenUserNameHasWhitespaces() {
        UserForm userForm = new UserForm("user 123","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameIsBelowLowerLimit() throws Exception {
        UserForm userForm = new UserForm("user","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameIsAboveUpperLimit() throws Exception {
        UserForm userForm = new UserForm("qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorWhenUserNameHasSpecialCharacters() throws Exception {
        UserForm userForm = new UserForm("qwertyuioplkjhgfdsazxcvbnmqwertyu@#","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));

    }

    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordIsBelowLowerLimit() throws Exception {
        UserForm userForm = new UserForm("username","role","name","email@email.com", "passw");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));
    }


    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordIsAboveUpperLimit() throws Exception {
        UserForm userForm = new UserForm("username","role","name","email@email.com", "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));
    }

    @Test
    public void shouldReturnInvalidPasswordErrorWhenPasswordHasSpecialCharacters() throws Exception {
        UserForm userForm = new UserForm("username","role","name","email@email.com", "password134234*&^fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));

    }


    @Test
    public void shouldReturnInvalidNameErrorWhenNameExceedsUpperLimit() {
        UserForm userForm = new UserForm("username","role","qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidName")));
    }
    @Test
    public void shouldReturnInvalidNameErrorWhenNameIsOnlyWhiteSpaces() {
        UserForm userForm = new UserForm("username","role","         ","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("allFieldsAreMandatory")));
    }

    @Test
    public void shouldValidateEmail() throws Exception {
        UserForm userForm = new UserForm("username","role","name","emailemail.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidEmail")));
    }

    @Test
    public void shouldReturnNoErrorsWhenAllCredentialsAreValid() {
        UserForm userForm = new UserForm("user123","role","name","email@email.com", "password134234fsd");
        userForm.validate();
        assertThat(userForm.hasErrors(),is(false));
    }

}
