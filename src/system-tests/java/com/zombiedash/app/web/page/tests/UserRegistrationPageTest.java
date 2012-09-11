package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
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
    UUID confId = populateWithOneConference();
    openConferenceRegistrationPage(confId.toString());
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
    UUID confId = populateWithOneConference();
    registerExampleValidAttendee(confId);
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Registration Confirmed")));
  }

  private void registerExampleValidAttendee(UUID confId) {
    openConferenceRegistrationPage(confId.toString());
    browser.inputTextOn("userName","ExampleUsername")
        .inputTextOn("password","Password1")
        .inputTextOn("password2", "Password1")
        .inputTextOn("email","example@email.com")
        .inputTextOn("name","Example Name")
        .inputTextOn("dob","1950-01-01")
        .selectFromDropDown("countrylist","India")
        .inputTextOn("phoneNo", "1-555-555-5555")
        .inputTextOn("address","Example Address")
        .inputTextOn("zipcode", "55555");
    browser.clickOn("submit");
  }

  @Ignore
  @Test
  public void shouldReturnToSamePageWhenFieldsAreEmpty() {
    UUID confId = populateWithOneConference();
    openConferenceRegistrationPage(confId.toString());
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
    UUID uuidConference = populateWithOneConference();
    registerExampleValidAttendee(uuidConference);
    registerExampleValidAttendee(uuidConference);
    assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Attendee Registration")));
  }
}
