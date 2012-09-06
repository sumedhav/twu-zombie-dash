package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AuthenticationTest extends BasePageTest {

    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .usingHttps()
                .loggedInAsAdmin()
                .build();

        assertThat(browser.currentUrl(), browser.getPageTitle(), is("Zombie Dash : Welcome"));
    }

    @Test
    public void shouldGoToLoginPageAfterLogout() {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .usingHttps()
                .loggedInAsAdmin()
                .build();
        browser.findElement(By.id("Logout")).click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        assertThat(browser.findElement(By.id("logout_message")).getText(), is("You have logged out successfully"));
    }

    @Test
    public void shouldStayInLoginFormAndDisplayErrorMessageWhenLoginUnsuccessful() throws Exception {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .usingHttps()
                .loggedInAs("noooo", "wrong!")
                .build();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        assertThat(browser.findElement(By.id("message_to_be_displayed")).getText(), is("The username or password you entered is incorrect"));
    }
}
