package com.zombiedash.app.validator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;

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
    assertThat(model.get("numberError"), is(equalTo("invalid data")));
  }

  @Test
  public void consistentNumbersStringsShouldBeTrue() {
    String badString = "1";
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedValue = conferenceValidator.isValidNumber(badString, new HashMap<String, String>());
    assertThat(expectedValue, is(equalTo(true)));
  }

  @Test
  public void emptyFieldsShouldReturnFalse() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isNotEmpty("", "", "", "", "", "", "", new HashMap<String, String>());
    assertThat(expectedTruthValue,is(equalTo(false)));
  }

  @Test
  public void nonemptyFieldsShouldReturnTrue() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isNotEmpty("na", "na", "na", "na", "na", "na", "na", new HashMap<String, String>());
    assertThat(expectedTruthValue,is(equalTo(true)));
  }

  @Test
  public void emptyFieldsShouldMutateModelParameterWithErrorString() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    Map<String, String> model = new HashMap<String, String>();
    conferenceValidator.isNotEmpty("", "", "", "", "", "", "", model);
    assertThat(model.size(), is(equalTo(1)));
    assertThat(model.get("errorString"), is(equalTo("ALL FIELDS ARE COMPULSORY")));
  }

  @Test
  public void invalidDatesShouldReturnFalse() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isValidDate("monday",new HashMap<String, String>(), "dateError");
    assertThat(expectedTruthValue,is(equalTo(false)));
  }

  @Test
  public void invalidDatesShouldMutateModelParameterWithErrorString() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    Map<String,String> model = new HashMap<String, String>();
    conferenceValidator.isValidDate("2012-03-0",model, "dateError");
    assertThat(model.size(), is(equalTo(1)));
    assertThat(model.get("dateError"), is(equalTo("Date must be in yyyy-MM-dd format")));
  }

  @Test
  public void validDatesShouldReturnTrue() {
    ConferenceValidator conferenceValidator = new ConferenceValidator();
    boolean expectedTruthValue = conferenceValidator.isValidDate("2012-03-04", new HashMap<String, String>(), "dateError");
    assertThat(expectedTruthValue, is(equalTo(true)));
  }
}
