package com.zombiedash.app.forms;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class RegistrationFormTest {
    @Test
    public void shouldReturnInvalidNameErrorWhenNameIsInvalid(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUserName("Invalid Name");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("invalidUserName"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnEmptyNameErrorIfNameIsEmpty(){
        RegistrationForm registrationForm = new RegistrationForm();
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("usernameFieldEmpty"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnInvalidAddressErrorWhenAddressIsInvalid(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setAddress(
                "Invalid AddressXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("invalidAddress"),is(equalTo(true)));
    }
    @Test
    public void shouldReturnInvalidPhoneNumberErrorWhenPhoneNumberIsInvalid(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setPhoneNo("123456789012345678901");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("invalidPhoneNo"),is(equalTo(true)));
    }
    @Test
    public void shouldReturnInvalidZipcodeErrorWhenZipcodeIsInvalid(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setZipcode("123456789012345678901");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("invalidZipcode"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnEmptyAddressErrorIfAddressIsEmpty() {
        RegistrationForm registrationForm = new RegistrationForm();
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("addressFieldEmpty"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnPasswordMismatchErrorIfPasswordDoNotMatch(){
        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setPassword("Password1");
        registrationForm.setPassword2("Password2");
        List<String> errorCodes = registrationForm.validate();
        assertThat(errorCodes.contains("passwordMismatch"),is(equalTo(true)));
    }

}
