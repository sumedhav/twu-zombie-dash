package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginLogoutTest {

    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/login/LoginForm");

        WebElement usernameElement = browser.findElement(By.id("Username"));
        usernameElement.sendKeys("admin");

        WebElement passwordElement = browser.findElement(By.id("Password"));
        passwordElement.sendKeys("Welcome1");

        WebElement submitElement = browser.findElement(By.id("Submit"));
        submitElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Welcome"));

        WebElement logoutElement = browser.findElement(By.id("Logout"));
        logoutElement.click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
        WebElement messageElement = browser.findElement(By.id("message_to_be_displayed"));

        assertThat(messageElement.getText(), is("You have been logged out successfully!!"));

    }

    @Test
    public void shouldStayInLoginFormIfLoginUnsuccessful() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/login/LoginForm");

        WebElement usernameElement = browser.findElement(By.id("Username"));
        usernameElement.sendKeys("admin1");

        WebElement passwordElement = browser.findElement(By.id("Password"));
        passwordElement.sendKeys("12Welcome1");

        WebElement submitElement = browser.findElement(By.id("Submit"));
        submitElement.click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
    }
}
