package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Before;
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
        jdbcTemplate.execute("DELETE zombie_conference");
        conferenceRepository = new ConferenceRepository(jdbcTemplate);
        UUID conferenceId = UUID.randomUUID();
        conferenceRepository.saveConference(
                new Conference(conferenceId,"Java","Java","Java","here","2013-01-01","2013-01-05",100));
        return conferenceId;
    }

    @Test
    public void shouldGoToUserRegistrationPageIfConferenceExists(){
        UUID confId = populateWithOneConference();
        openConferenceRegistrationPage(confId);
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Attendee Registration")));
    }

    @Test
    public void shouldGoTo404PageIfConferenceDoesNotExist(){
        populateWithOneConference();
        openConferenceRegistrationPage(UUID.randomUUID());
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Page Not Found")));
    }


    private void openConferenceRegistrationPage(UUID conferenceId) {
        browser.open("/app/zombie/register/" + conferenceId.toString());
    }

    @Test
    public void shouldRegisterAnAttendeeWithValidInformation() {
        UUID confId = populateWithOneConference();
        openConferenceRegistrationPage(confId);
        browser.inputTextOn("userName","ExampleUsername")
               .inputTextOn("password","Password1")
               .inputTextOn("password2", "Password1")
               .inputTextOn("email","example@email.com")
               .inputTextOn("name","Example Name")
               .inputTextOn("dob","1950-01-01")
               .selectFromDropDown("countrylist","India")
               .inputTextOn("phoneno", "1-555-555-5555")
               .inputTextOn("address","Example Address")
               .inputTextOn("zipcode","55555");

        browser.clickOn("submit");
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Registration Confirmed")));
    }
}
