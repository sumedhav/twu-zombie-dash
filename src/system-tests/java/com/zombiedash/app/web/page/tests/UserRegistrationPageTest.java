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
    registerExampleValidAttendee(conferenceId, "ExampleUsername");
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Registration Confirmed")));
  }

  private void registerExampleValidAttendee(UUID conferenceId, String username) {
    openConferenceRegistrationPage(conferenceId.toString());
    browser.inputTextOn("phoneNo", "1-555-555-5555")
        .inputTextOn("address","Example Address")
        .inputTextOn("zipcode", "55555");
    fillOutMandatoryFields(username);
    browser.clickOn("submit");
  }

  private void registerExampleMinimalValidAttendee(UUID conferenceId, String username) {
    openConferenceRegistrationPage(conferenceId.toString());
    fillOutMandatoryFields(username);
    browser.clickOn("submit");
  }

  private void fillOutMandatoryFields(String username) {
    browser.inputTextOn("userName",username)
        .inputTextOn("password","Password1")
        .inputTextOn("password2", "Password1")
        .inputTextOn("email","example@email.com")
        .inputTextOn("name","Example Name")
        .inputTextOn("dob","1950-01-01")
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
    registerExampleValidAttendee(conferenceId, "ExampleUsername");
    registerExampleValidAttendee(conferenceId, "ExampleUsername");
    assertThat(browser.getTextById("error_message_div"), is(equalTo("Someone already has that username. Try another.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorMessageOnShortUsername() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendee(conferenceId,"Bob");
    assertThat(browser.getTextById("invalid_user_name"), is(equalTo("Username must have 5-40 alphanumeric characters and no whitespaces.")));
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }

  @Test
  public void shouldShowInlineErrorMessageOnExcessivelyLongUsername() {
    UUID conferenceId = populateWithOneConference();
    registerExampleMinimalValidAttendee(conferenceId, StringUtils.repeat("X",41));
    assertThat(browser.getTextById("invalid_user_name"), is(equalTo("Username must have 5-40 alphanumeric characters and no whitespaces.")));
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Attendee Registration")));
  }
}
