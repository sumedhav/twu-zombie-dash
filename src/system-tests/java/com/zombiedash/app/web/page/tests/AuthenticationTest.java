package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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

        //todo: move this into ANOTHER test
//        statelessBrowser.findElement(By.id("Logout")).click();
//
//        assertThat(statelessBrowser.getPageTitle(), is("Zombie Dash : Login"));
//
//        assertThat(statelessBrowser.findElement(By.id("message_to_be_displayed")).getText(), is("You have been logged out successfully!!"));
    }

    @Test
    @Ignore("WIP: logout behaviour changed with Spring security")
    public void shouldStayInLoginFormIfLoginUnsuccessful() throws Exception {
        Browser browser = Application.statelessBrowser();
        browser.open("/zombie/login/LoginForm");

        WebElement usernameElement = browser.findElement(By.name("username"));
        usernameElement.sendKeys("admin1");

        WebElement passwordElement = browser.findElement(By.name("password"));
        passwordElement.sendKeys("12Welcome1");

        WebElement submitElement = browser.findElement(By.tagName("button"));
        submitElement.click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
    }
}
