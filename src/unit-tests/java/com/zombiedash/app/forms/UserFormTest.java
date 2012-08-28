package com.zombiedash.app.forms;

import org.junit.Test;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class UserFormTest {

    @Test
    public void shouldReturnAnErrorMessageIfAnyFieldIsEmpty () throws Exception {
        UserForm userForm = new UserForm("",null,"name","email@email.com","password1");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("allFieldsAreMandatory") ));
    }

    @Test
    public void shouldReturnInvalidPasswordErrorForInvalidPassword() throws Exception {
        UserForm userForm = new UserForm("username","role","name","email@email.com", "pass");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidPassword")));
    }

    @Test
    public void shouldReturnInvalidUserNameErrorForInvalidUserName() throws Exception {
        UserForm userForm = new UserForm("user","role","name","email@email.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidUserName")));
    }

    @Test
    public void shouldValidateEmail() throws Exception {
        UserForm userForm = new UserForm("username","role","name","emailemail.com", "password134234fsd");
        List<String> result = userForm.validate();
        assertThat(result.get(0),is(equalTo("invalidEmail")));
    }

}
