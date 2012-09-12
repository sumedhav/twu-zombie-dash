package com.zombiedash.app.forms;

import com.zombiedash.app.model.Attendee;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class RegistrationFormTest {
    private RegistrationForm registrationForm;

    @Before
    public void setUp() throws Exception {
        registrationForm = new RegistrationForm();
    }

    @Test
    public void shouldReturnInvalidNameErrorWhenNameIsInvalid(){
        registrationForm.setUserName("Invalid Name");
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("invalidUserName"), is(equalTo(true)));
    }

    @Test
    public void shouldReturnEmptyNameErrorIfNameIsEmpty(){
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("usernameFieldEmpty"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnInvalidDateFormatErrorIfDOBFormatIsIncorrect() throws Exception {
        registrationForm.setDob("2003-2-29");
        registrationForm.validate();
        assertThat(registrationForm.getErrors().contains("invalidDateFormat"), is(equalTo(true)));
    }

    @Test
    public void shouldReturnInvalidDOBErrorIfDOBIsAFutureDate() throws Exception {
        registrationForm.setDob("3020-1-12");
        registrationForm.validate();
        assertThat(registrationForm.getErrors().contains("invalidDOB"), is(equalTo(true)));
    }

    @Test
    public void shouldReturnInvalidDOBErrorIfDOBIsBeforeYear1900() throws Exception {
        registrationForm.setDob("1880-01-09");
        registrationForm.validate();
        assertThat(registrationForm.getErrors().contains("invalidDOB"), is(equalTo(true)));
    }

    @Test
    public void shouldAcceptADOBBetween1900AndCurrentDate() throws Exception {
        registrationForm.setDob("1910-02-06");
        registrationForm.validate();
        assertThat(registrationForm.getErrors().contains("invalidDOB"), is(equalTo(false)));
    }

    @Test
    public void shouldFailIfNoCountryIsSelected() throws Exception {
        registrationForm.setCountrylist("");
        registrationForm.validate();
        assertThat(registrationForm.getErrors().contains("countryNotSelected"), is(equalTo(true)));
    }

    @Test
    public void shouldReturnInvalidAddressErrorWhenAddressIsInvalid(){
        registrationForm.setAddress(
                "Invalid AddressXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("invalidAddress"),is(equalTo(true)));
    }
    @Test
    public void shouldReturnInvalidPhoneNumberErrorWhenPhoneNumberIsInvalid(){
        registrationForm.setPhoneNo("123456789012345678901");
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("invalidPhoneNo"),is(equalTo(true)));
    }
    @Test
    public void shouldReturnInvalidZipcodeErrorWhenZipcodeIsInvalid(){
        registrationForm.setZipcode("123456789012345678901");
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("invalidZipcode"),is(equalTo(true)));
    }

    @Test
    public void shouldReturnPasswordMismatchErrorIfPasswordDoNotMatch(){
        registrationForm.setPassword("Password1");
        registrationForm.setPassword2("Password2");
        registrationForm.validate();
        List<String> errorCodes = registrationForm.getErrors();
        assertThat(errorCodes.contains("passwordMismatch"),is(equalTo(true)));
    }

    @Test
    public void shouldTellYouIfThereAreAnyErrors() throws Exception {
        registrationForm.setEmail("sadasdas");
        registrationForm.validate();
        assertThat(registrationForm.hasErrors(), is(true));
    }

    @Test
    public void shouldPopulateFromValuesToModelMap() throws Exception {
        registrationForm = registrationFormFactory("sadasdas","Hari Prasad", "sadasdas@email.com", "1990-01-01", "India", "address", "1241324", "zipcode", "on", "");

        HashMap<String, String> expectedMap = new HashMap<String, String>();
        expectedMap.put("username", "sadasdas");
        expectedMap.put("name", "Hari Prasad");
        expectedMap.put("email", "sadasdas@email.com");
        expectedMap.put("dob", "1990-01-01");
        expectedMap.put("address", "address");
        expectedMap.put("countrylist", "India");
        expectedMap.put("zipCode", "zipcode");
        expectedMap.put("phoneNo", "1241324");
        expectedMap.put("productsMailingConfirmation", "on");
        expectedMap.put("adsConfirmation", "");

        Map map = registrationForm.populateFormValuesToModelMap();

        assertThat((HashMap<String, String>) map, is(equalTo(expectedMap)));
    }

    @Test
    public void shouldCreateAttendee() throws Exception {
        registrationForm = registrationFormFactory("sadasdas","Hari Prasad", "sadasdas@email.com", "1990-01-01", "India", "address", "1241324", "zipcode", "on", "on");

        Attendee attendee = registrationForm.createAttendee();

        assertThat(attendee.getUsername(), is("sadasdas"));
        assertThat(attendee.getName(), is("Hari Prasad"));
        assertThat(attendee.getEmail(), is("sadasdas@email.com"));
        assertThat(attendee.getDob(), is("1990-01-01"));
        assertThat(attendee.getCountry(), is("India"));
        assertThat(attendee.getAddress(), is("address"));
        assertThat(attendee.getPhoneNo(), is("1241324"));
        assertThat(attendee.getZipcode(), is("zipcode"));
        assertThat(attendee.getProductsMailingConfirmation(), is(true));
        assertThat(attendee.getAdsConfirmation(), is(true));
    }

    private RegistrationForm registrationFormFactory(String userName, String name, String email, String dob, String country, String address, String phoneNo, String zipcode, String productsMailingConfirmation, String adsConfirmation) {

        RegistrationForm registrationForm = new RegistrationForm();
        registrationForm.setUserName(userName);
        registrationForm.setName(name);
        registrationForm.setEmail(email);
        registrationForm.setDob(dob);
        registrationForm.setCountrylist(country);
        registrationForm.setAddress(address);
        registrationForm.setPhoneNo(phoneNo);
        registrationForm.setZipcode(zipcode);
        registrationForm.setProductsMailingConfirmation(productsMailingConfirmation);
        registrationForm.setAdsConfirmation(adsConfirmation);
        return registrationForm;
    }
}
