package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.UserTestDataManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticationTest extends BasePageTest {
    private JdbcTemplate jdbcTemplate;


    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        browser = BrowserSessionBuilder.buildHttpsAdminSession();
        assertThat(browser.currentUrl(), browser.getPageTitle(), is("Zombie Dash : Welcome"));
    }

    @Test
    public void shouldGoToLoginPageAfterLogout() {
        browser = BrowserSessionBuilder.buildHttpsAdminSession();
        browser.findElement(By.id("Logout")).click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        assertThat(browser.findElement(By.id("logout_message")).getText(), is("You have logged out successfully"));
    }

    @Test
    public void shouldStayInLoginFormAndDisplayErrorMessageWhenLoginUnsuccessful() throws Exception {
        browser = BrowserSessionBuilder.aBrowserSession().usingHttps().loggedInAs("noooo", "wrong!").build();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        assertThat(browser.findElement(By.id("message_to_be_displayed")).getText(), is("The username or password you entered is incorrect"));
    }

    @Test
    @Rollback(true)
    public void shouldGoToAttendeeLoginSuccessPageIfUserIsAttendee() throws Exception {
        browser = BrowserSessionBuilder.aBrowserSession().usingHttps().build();
        setUpAttendeeInDb();
        browser.loginAs("attendee","password1");
        assertThat(browser.getPageTitle(),is("Attendee Home"));
    }

    private void setUpAttendeeInDb() {
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        UserTestDataManager userTestDataManager = new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
        userTestDataManager.insertAttendeeWithGenericConference("attendee","password1");
    }

    private void setUpGameDesignerInDb() {
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        UserTestDataManager userTestDataManager = new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
        userTestDataManager.insertUser("gamedesigner","password1", Role.GAME_DESIGNER,"gameDesignerName","email@email.com");
    }

    @Test
    @Rollback(true)
    public void shouldGoToGameDesignerHomePageIfUserIsGameDesigner(){
        browser = BrowserSessionBuilder.aBrowserSession().usingHttps().build();
        setUpGameDesignerInDb();
        browser.loginAs("gamedesigner","password1");
        assertThat(browser.getPageTitle(),is("Zombie Dash : Game Designer Home"));
    }
}
