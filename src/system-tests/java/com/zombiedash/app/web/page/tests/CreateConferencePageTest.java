package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateConferencePageTest {
    Browser browser;

    @Before
    public void adminLogsIn() {
        browser = BrowserSessionBuilder
                .buildHttpsAdminSession();
    }

    @Test
    public void clickOnCreateConferenceShouldOpenCreateConferencePage() {
        browser.open("/app/zombie/admin/conference/list");
        browser.clickOn("conference_creation");
        assertThat(browser.getPageTitle(), is(equalTo("Create Conference Page")));
    }

    @Test
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
        assertThat(browser.getTextById("existing_conference_id_1"), is(equalTo("Conference Name")));
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
