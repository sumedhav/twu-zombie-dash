package com.zombiedash.app.validator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class ConferenceValidatorTest {
  @Test
  public void invalidNumbersStringsShouldBeFalse() {
    String badString = "one";
    String otherBadString = "-1";
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedValue = conferenceValidator.isValidNumber(badString, new HashMap<String, String>());
    boolean otherExpectedValue = conferenceValidator.isValidNumber(otherBadString, new HashMap<String, String>());
    assertThat(expectedValue, is(equalTo(false)));
    assertThat(otherExpectedValue, is(equalTo(false)));
  }

  @Test
  public void invalidNumberStringsShouldMutateModelParameterWithErrorString() {
    String badString = "one";
    Map<String,String> model = new HashMap<String, String>();
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    conferenceValidator.isValidNumber(badString, model);
    assertThat(model.size(), is(equalTo(1)));
    assertThat(model.get("numberError"), is(equalTo("Must be a positive integer")));
  }

  @Test
  public void consistentNumbersStringsShouldBeTrue() {
    String badString = "1";
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedValue = conferenceValidator.isValidNumber(badString, new HashMap<String, String>());
    assertThat(expectedValue, is(equalTo(true)));
  }

  @Test
  public void invalidDatesShouldReturnFalse() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isValidDate("monday",new HashMap<String, String>(),"dateError");
    assertThat(expectedTruthValue,is(equalTo(false)));
  }

  @Test
  public void invalidDatesShouldMutateModelParameterWithErrorString() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    Map<String,String> model = new HashMap<String, String>();
    conferenceValidator.isValidDate("2012-03-0",model,"dateError");
    assertThat(model.size(), is(equalTo(1)));
    assertThat(model.get("dateError"), is(equalTo("Must be in yyyy-mm-dd format")));
  }

  @Test
  public void validDatesShouldReturnTrue() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isValidDate("2012-03-04", new HashMap<String, String>(),"dateError");
    assertThat(expectedTruthValue, is(equalTo(true)));
  }

    @Test
    public void emptyFieldShouldReturnFalse() {
        ConferenceValidator conferenceValidator = new ConferenceValidator();
        Map<String,String> model = new HashMap<String, String>();

        boolean isValidData = conferenceValidator.isFieldCompleted("", model, "fieldMissing");
        assertThat(isValidData,is(false));
    }

    @Test
    public void shouldAssignErrorMessageAndStarToEmptyField() {
        ConferenceValidator conferenceValidator = new ConferenceValidator();
        Map<String,String> model = new HashMap<String, String>();

        String missingFieldErrorName = "fieldMissing";
        conferenceValidator.isFieldCompleted("", model, missingFieldErrorName);
        assertThat(model.get(missingFieldErrorName),is("*"));
    }

    @Test
    public void shouldAssignGeneralMissingFieldsErrorIfFieldIsEmpty() {
        ConferenceValidator conferenceValidator = new ConferenceValidator();
        Map<String,String> model = new HashMap<String, String>();

        String missingFieldErrorName = "fieldMissing";
        conferenceValidator.isFieldCompleted("", model, missingFieldErrorName);
        assertThat(model.get("errorString"),is("All (*) fields are compulsory"));
    }

    @Test
    public void shouldAssignOnlyMissingFieldStarForEmptyDateField() {
        ConferenceValidator conferenceValidator = new ConferenceValidator();
        Map<String,String> model = new HashMap<String, String>();

        String dateFormatErrorName = "dateFormatError";
        conferenceValidator.isValidDate("", model, dateFormatErrorName);
        assertNull(model.get(dateFormatErrorName));
    }

    @Test
    public void shouldAssignOnlyMissingFieldStarForEmptyMaxAttendeesField() {
        ConferenceValidator conferenceValidator = new ConferenceValidator();
        Map<String,String> model = new HashMap<String, String>();

        conferenceValidator.isValidNumber("", model);
        assertNull(model.get("numberError"));
    }


}
