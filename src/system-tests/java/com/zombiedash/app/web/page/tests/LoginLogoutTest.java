package com.zombiedash.app.web.page.tests;

import com.example.app.jetty.WebServer;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginLogoutTest {

    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        WebServer webServer=new WebServer(1234);
        try {
            webServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebDriver driver=new FirefoxDriver();
        driver.get("http:/localhost:1234/zombie/login/LoginForm");

        WebElement usernameElement = driver.findElement(By.id("Username"));
        usernameElement.sendKeys("admin");

        WebElement passwordElement = driver.findElement(By.id("Password"));
        passwordElement.sendKeys("Welcome1");

        WebElement submitElement = driver.findElement(By.id("Submit"));
        submitElement.submit();
        assertThat(driver.getTitle(), is("Zombie Dash : Welcome"));

        WebElement logoutElement = driver.findElement(By.id("Logout"));
        logoutElement.click();

        assertThat(driver.getTitle(), is("Zombie Dash : Login"));
        WebElement messageElement = driver.findElement(By.id("message_to_be_displayed"));

        assertThat(messageElement.getText(), is("You have been logged out successfully!!"));
        driver.close();
        webServer.stop();

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
