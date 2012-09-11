package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserRegistrationPageTest extends BasePageTest{
  private JdbcTemplate jdbcTemplate;
  private ConferenceRepository conferenceRepository;

  @Before
  public void setupSession() {
    browser= BrowserSessionBuilder
        .aBrowserSession()
            .withJavascriptEnabled()
        .usingHttps()
        .loggedInAsAdmin()
        .build();
  }


  private UUID populateWithOneConference() {
    jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
    jdbcTemplate.execute("DELETE zombie_option");
    jdbcTemplate.execute("DELETE zombie_question");
    jdbcTemplate.execute("DELETE zombie_task");
    jdbcTemplate.execute("DELETE zombie_conference");
    conferenceRepository = new ConferenceRepository(jdbcTemplate);
    UUID conferenceId = UUID.randomUUID();
    conferenceRepository.insertConference(
        new Conference(conferenceId, "Java", "Java", "Java", "here", "2013-01-01", "2013-01-05", 100));
    return conferenceId;
  }

  @Test
  public void shouldGoToUserRegistrationPageIfConferenceExists(){
    UUID conferenceId = populateWithOneConference();
    openConferenceRegistrationPage(conferenceId.toString());
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldGoTo404PageIfConferenceDoesNotExist(){
    populateWithOneConference();
    UUID randomUUID = UUID.randomUUID();
    openConferenceRegistrationPage(randomUUID.toString());
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Page Not Found")));
  }

  @Test
  public void shouldGoTo404PageIfConferenceIDIsInvalidFormat(){
    populateWithOneConference();
    openConferenceRegistrationPage("1234");
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Page Not Found")));
  }

  private void openConferenceRegistrationPage(String conferenceId) {
    browser.open("/app/zombie/register/" + conferenceId);
  }

  @Test
  public void shouldRegisterAnAttendeeWithValidInformation() {
    UUID conferenceId = populateWithOneConference();
    registerExampleValidAttendeeWithReplacement(conferenceId, "userName", "ExampleUsername");
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Registration Confirmed")));
  }

  private void registerExampleValidAttendeeWithReplacement(UUID conferenceId, String field, String fieldReplacement) {
    openConferenceRegistrationPage(conferenceId.toString());
    browser.inputTextOn("phoneNo", "1-555-555-5555")
        .inputTextOn("address","Example Address")
        .inputTextOn("zipcode", "55555");
    fillOutMandatoryFieldsWithReplacement(field, fieldReplacement);
    browser.clickOn("submit");
  }

  private void registerExampleMinimalValidAttendeeWithReplacement(UUID conferenceId, String field, String fieldReplacement) {
    openConferenceRegistrationPage(conferenceId.toString());
    fillOutMandatoryFieldsWithReplacement(field, fieldReplacement);
    browser.clickOn("submit");
  }

  private void fillOutMandatoryFieldsWithReplacement(String field, String fieldReplacement) {
    browser.inputTextOn("userName","ExampleUsername")
        .inputTextOn("password","Password1")
        .inputTextOn("password2", "Password1")
        .inputTextOn("email","example@email.com")
        .inputTextOn("name","Example Name")
        .inputTextOn("dob","1950-01-01")
        .clearTextOn(field)
        .inputTextOn(field, fieldReplacement)
        .selectFromDropDown("countrylist","India");
  }

  @Ignore
  @Test
  public void shouldReturnToSamePageWhenFieldsAreEmpty() {
    UUID conferenceId = populateWithOneConference();
    openConferenceRegistrationPage(conferenceId.toString());
    browser.clickOn("submit");

    assertThatFieldHasInvalidData("username_field_empty", "You can't leave this field empty.");
    assertThatFieldHasInvalidData("password_field_empty", "You can't leave this field empty.");
    assertThatFieldHasInvalidData("name_field_empty", "You can't leave this field empty.");
    assertThatFieldHasInvalidData("dob_field_empty", "You can't leave this field empty.");
    assertThatFieldHasInvalidData("phoneno_field_empty", "");
    assertThatFieldHasInvalidData("zipcode_field_empty", "");
    assertThatFieldHasInvalidData("address_field_empty", "");
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  private void assertThatFieldHasInvalidData(String errorId, String errorMessage) {
    assertThat(browser.getTextById(errorId), is(equalTo(errorMessage)));
  }

  @Test
  public void shouldShowErrorMessageOnDuplicateUsername() {
    UUID conferenceId = populateWithOneConference();
    registerExampleValidAttendeeWithReplacement(conferenceId, "userName", "ExampleUsername");
    registerExampleValidAttendeeWithReplacement(conferenceId, "userName", "ExampleUsername");
    assertThat(browser.getTextById("error_message_div"), is(equalTo("Someone already has that username. Try another.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorMessageOnShortUsername() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId, "userName", "Bob");
    assertThat(browser.getTextById("invalid_user_name"), is(equalTo("Username must have 5-40 alphanumeric characters and no whitespaces.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test@Ignore
  public void shouldShowInlineErrorMessageOnExcessivelyLongUsername() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId, "userName", StringUtils.repeat("X", 41));
    assertThat(browser.getTextById("invalid_user_name"), is(equalTo("Username must have 5-40 alphanumeric characters and no whitespaces.")));
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorMessageOnShortPassword() {
    assertPasswordError("XXXXX");
  }

  @Test
  public void shouldShowInlineErrorMessageOnLongPassword() {
    assertPasswordError(StringUtils.repeat("X", 41));
  }

  @Test
  public void shouldShowInlineErrorMessageOnInvalidCharacterPassword() {
    assertPasswordError("XXXXX !@#$ggdsjfg");
  }

  @Test
  public void shouldShowInlineErrorMessageOnNonAlphaNumericPassword() {
    assertPasswordError("7674746466");
  }

  private void assertPasswordError(String password) {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId, "password", password);
    assertThat(browser.getTextById("invalid_password"), is(equalTo("Password must have 6-40 alphanumeric characters with at least one digit(s).")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorOnUnequalPassword() {
    UUID conferenceId = populateWithOneConference();
    openConferenceRegistrationPage(conferenceId.toString());
    fillOutMandatoryFieldsWithReplacement("password", "password1");
    browser.clearTextOn("password2")
        .inputTextOn("password2","password2")
        .clickOn("submit");
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorOnInvalidEmail() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId, "email", "boo");
    assertThat(browser.getTextById("invalid_email"), is(equalTo("Please enter a valid email address.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorOnInvalidName() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId, "name", "Alfred1");
    assertThat(browser.getTextById("invalid_name"), is(equalTo("Name should not exceed 40 characters and should not contain digits, special characters.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldNotBeAbleToEnterCharactersInDobFields() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId,"dob","abcd");
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorOnInvalidDateOfBirth() {
    assertInvalidDateError("1234567890", "Enter a date in yyyy-mm-dd format.", "invalid_date_format");
  }

  @Ignore
  @Test
  public void shouldShowInlineErrorOnFutureDate() {
    assertInvalidDateError("3000-11-03", "Hmm, the date doesn’t look right. Be sure to use your actual DOB.", "invalid_dob");
  }

  @Ignore
  @Test
  public void shouldShowInlineErrorOnPreTwentiethCenturyDate() {
    assertInvalidDateError("1800-11-03", "Hmm, the date doesn’t look right. Be sure to use your actual DOB.", "invalid_dob");
  }

  private void assertInvalidDateError(String dob, String errorMessage, String field) {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendeeWithReplacement(conferenceId,"dob",dob);
    assertThat(browser.getTextById(field), is(equalTo(errorMessage)));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }
}
