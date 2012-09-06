package com.zombiedash.app.forms;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegistrationFormTest {
    @Test
    public void shouldReturnInvalidNameErrorWhenNameIsInvalid(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUserName("Invalid Name");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("invalidUserName"),is(equalTo(true)));
    }


}
