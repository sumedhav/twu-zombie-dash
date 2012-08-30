package com.zombiedash.app.web.page.tests;

import com.example.app.jetty.WebServer;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Ignore("WIP: need to remove instantiation of FirefoxDriver")
public class UserDetailsPageTest {

    @Test
    public void shouldGoToUserDetailsPageOnClickingBack() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/users/display/admin");

        WebElement backElement = browser.findElement(By.id("back_user_details"));
        backElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

    @Test
    public void shouldRemainOnUserDetailsPageWhenClickedCancelOnAlertBox() {
        WebServer webServer = new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/admin/users/display/admin");
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : User Details"));

            WebElement deleteElement = webDriver.findElement(By.id("delete_user"));
            deleteElement.click();
            Alert alert = webDriver.switchTo().alert();
            alert.dismiss();
            Thread.sleep(3000);
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : User Details"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webServer.stop();
            webDriver.close();
        }
    }

    @Test
    public void shouldDeleteUserWhenClickedOkOnAlertBox() {

        WebServer webServer = new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/admin/users/create");

            WebElement usernameElement = webDriver.findElement(By.id("username"));
            usernameElement.sendKeys("username");

            WebElement passwordElement = webDriver.findElement(By.id("password"));
            passwordElement.sendKeys("password123");

            WebElement nameElement = webDriver.findElement(By.id("name"));
            nameElement.sendKeys("yahya");

            WebElement emailElement = webDriver.findElement(By.id("email"));
            emailElement.sendKeys("email@email.com");

            WebElement saveElement = webDriver.findElement(By.id("submit"));
            saveElement.click();

            webDriver.get("http://localhost:1234/zombie/admin/users/display/username");
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : User Details"));

            WebElement deleteElement = webDriver.findElement(By.id("delete_user"));
            deleteElement.click();
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
            Thread.sleep(3000);
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : User List"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webServer.stop();
            webDriver.close();
        }

    }
}
