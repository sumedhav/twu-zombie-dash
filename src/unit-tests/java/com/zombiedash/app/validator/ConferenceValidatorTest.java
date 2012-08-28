package com.zombiedash.app.validator;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class ConferenceValidatorTest {
    private  ConferenceValidator conferenceValidator;
    private Map<String,String> model;

    @Before
    public void setUp() {
        conferenceValidator = new ConferenceValidator();
        model = new HashMap<String, String>();
    }

    @Test
    public void invalidNumbersStringsShouldBeFalse() {
        String badString = "one";
        String otherBadString = "-1";
        boolean expectedValue = conferenceValidator.isValidNumber(badString, model);
        boolean otherExpectedValue = conferenceValidator.isValidNumber(otherBadString, model);
        assertThat(expectedValue, is(equalTo(false)));
        assertThat(otherExpectedValue, is(equalTo(false)));
    }

    @Test
    public void invalidNumberStringsShouldMutateModelParameterWithErrorString() {
        String badString = "one";
        conferenceValidator.isValidNumber(badString, model);
        assertThat(model.size(), is(equalTo(1)));
        assertThat(model.get("numberError"), is(equalTo("Must be a positive integer")));
    }

    @Test
    public void consistentNumbersStringsShouldBeTrue() {
        String badString = "1";
        boolean expectedValue = conferenceValidator.isValidNumber(badString, model);
        assertThat(expectedValue, is(equalTo(true)));
    }

    @Test
    public void invalidDatesShouldReturnFalse() {
        boolean expectedTruthValue = conferenceValidator.isValidDate("monday",model,"dateError");
        assertThat(expectedTruthValue,is(equalTo(false)));
    }

    @Test
    public void invalidDatesShouldMutateModelParameterWithErrorString() {
        conferenceValidator.isValidDate("2012-03-0",model,"dateError");
        assertThat(model.size(), is(equalTo(1)));
        assertThat(model.get("dateError"), is(equalTo("Must be in yyyy-mm-dd format")));
    }

    @Test
    public void validDatesShouldReturnTrue() {
        boolean expectedTruthValue = conferenceValidator.isValidDate("2013-03-04", model,"dateError");
        assertThat(expectedTruthValue, is(equalTo(true)));
    }

    @Test
    public void emptyFieldShouldReturnFalse() {
        boolean isValidData = conferenceValidator.isCompletedField("", model, "fieldMissing");
        assertThat(isValidData,is(false));
    }

    @Test
    public void shouldAssignErrorMessageAndStarToEmptyField() {
        String missingFieldErrorName = "fieldMissing";
        conferenceValidator.isCompletedField("", model, missingFieldErrorName);
        assertThat(model.get(missingFieldErrorName),is("*"));
    }

    @Test
    public void shouldAssignGeneralMissingFieldsErrorIfFieldIsEmpty() {
        String missingFieldErrorName = "fieldMissing";
        conferenceValidator.isCompletedField("", model, missingFieldErrorName);
        assertThat(model.get("errorString"),is("All (*) fields are compulsory"));
    }

    @Test
    public void shouldAssignOnlyMissingFieldStarForEmptyDateField() {
        String dateFormatErrorName = "dateFormatError";
        conferenceValidator.isValidDate("", model, dateFormatErrorName);
        assertNull(model.get(dateFormatErrorName));
    }

    @Test
    public void shouldAssignOnlyMissingFieldStarForEmptyMaxAttendeesField() {
        conferenceValidator.isValidNumber("", model);
        assertNull(model.get("numberError"));
    }

    @Test
         public void pastDateShouldReturnFalseUponValidation()  {
        boolean isValid = conferenceValidator.isValidDate("2012-04-01", model, "futureDateError");
        assertThat(isValid, is(equalTo(false)));
    }

    @Test
    public void currentDateShouldReturnTrueUponValidation()  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        boolean isValid = conferenceValidator.isValidDate(dateFormat.format(currentDate).toString(), model, "futureDateError");
        assertThat(isValid, is(equalTo(true)));
    }

    @Test
    public void shouldReturnFalseIfEndDateIsBeforeStartDate() throws Exception {
        boolean isValid = conferenceValidator.isEndAfterStartDate("2013-10-20", "2013-08-20", model, "endDateError");
        assertThat(isValid, is(false));
    }

    @Test
    public void shouldReturnTrueIfEndDateIsSameAsStartDate() throws Exception {
        boolean isValid = conferenceValidator.isEndAfterStartDate("2013-10-20", "2013-10-20", model, "endDateError");
        assertThat(isValid, is(true));
    }

    @Test
         public void shouldReturnTrueIfEndDateIsAfterStartDate() throws Exception {
        boolean isValid = conferenceValidator.isEndAfterStartDate("2013-10-20", "2013-10-21", model, "endDateError");
        assertThat(isValid, is(true));
    }

    @Test
    public void shouldReturnFalseForInvalidDate() throws Exception {
        boolean isValid = conferenceValidator.isValidDate("2013-14-20", model, "dateError");
        assertThat(isValid, is(false));
    }

}
