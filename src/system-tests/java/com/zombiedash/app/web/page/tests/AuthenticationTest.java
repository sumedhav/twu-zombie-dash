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
                .newStatelessSession()
                .loggedInAsAdmin()
                .build();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Welcome"));
    }

    @Test
    public void shouldGoToLoginPageAfterLogout() {
        browser = BrowserSessionBuilder.newStatelessSession().loggedInAsAdmin().build();
        browser.findElement(By.id("Logout")).click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        assertThat(browser.findElement(By.id("message_to_be_displayed")).getText(), is("You have logged out successfully"));
    }

    @Test
    public void shouldStayInLoginFormIfLoginUnsuccessful() throws Exception {
        browser = BrowserSessionBuilder
                .newStatelessSession()
                .loggedInAs("noooo", "wrong!")
                .build();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
    }
}
