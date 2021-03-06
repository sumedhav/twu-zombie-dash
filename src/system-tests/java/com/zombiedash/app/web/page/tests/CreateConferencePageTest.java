package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateConferencePageTest {
    Browser browser;
    private JdbcTemplate jdbcTemplate;

    @Before
    public void adminLogsIn() {
        browser = BrowserSessionBuilder
                .buildHttpsAdminSession();
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        jdbcTemplate.execute("DELETE zombie_conference");
    }

    @Test
    public void clickOnCreateConferenceShouldOpenCreateConferencePage() {
        browser.open("/app/zombie/admin/conference/list");
        browser.clickOn("conference_creation");
        assertThat(browser.getPageTitle(), is(equalTo("Create Conference Page")));
    }

    @Test
    @Rollback(true)
    public void shouldSaveConferenceWhenAllFieldsAreValid() throws Exception {
        browser.open("/app/zombie/admin/conference/create");
        browser.inputTextOn("conf_name", "Conference Name")
                .inputTextOn("conf_topic", "Conference Topic")
                .inputTextOn("conf_start_date", "2100-04-06")
                .inputTextOn("conf_end_date", "2100-04-07")
                .inputTextOn("conf_description", "Conference description")
                .inputTextOn("conf_venue", "Conference Venue")
                .inputTextOn("conf_max_attendees", "200");
        browser.clickOn("submit");
        assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Conference Home")));
    }

    @Test
    public void adminClickOnCancelToLeaveThePage() {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .withJavascriptEnabled()
                .usingHttps()
                .loggedInAsAdmin()
                .build();
        browser.open("/app/zombie/admin/conference/create");
        browser.clickOn("cancel");
        browser.alertOk();
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Conference Home")));
    }

    @Test
    public void adminClickOnCancelAndCancelOnAlertBoxToStayOnPage() {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .withJavascriptEnabled()
                .usingHttps()
                .loggedInAsAdmin()
                .build();
        browser.open("/app/zombie/admin/conference/create");
        browser.clickOn("cancel");
        browser.alertCancel();
        assertThat(browser.getPageTitle(),is(equalTo("Create Conference Page")));
    }

    @Test
    public void shouldBeOnTheSamePageOfConferenceWhenSomeOrAllFieldsAreInvalid() throws Exception {
        browser.open("/app/zombie/admin/conference/create");
        browser.inputTextOn("conf_name", "Conference Name")
                .inputTextOn("conf_topic", "Conference Topic")
                .inputTextOn("conf_start_date", "2100-04-08")
                .inputTextOn("conf_end_date", "2100-04-07")
                .inputTextOn("conf_description", "Conference description")
                .inputTextOn("conf_venue", "Conference Venue")
                .inputTextOn("conf_max_attendees", "200");
        browser.clickOn("submit");
        assertThat(browser.getPageTitle(), is(equalTo("Create Conference Page")));
    }

    @Test
    public void shouldShowErrorMessageWhenFieldsAreEmpty() throws Exception {
        browser.open("/app/zombie/admin/conference/create");
        browser.clickOn("submit");
        assertThat(browser.getTextById("errorConferenceName"),is(equalTo("You can't leave this field empty.")));
        assertThat(browser.getPageTitle(), is(equalTo("Create Conference Page")));
    }
}
