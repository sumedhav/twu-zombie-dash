package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.jetty.WebServer;
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
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


@Ignore("WIP: need to implement common login behaviour")
public class CreateUserPageTest {

    @Test
    public void shouldDisplayErrorMessageWhenAnyFieldIsEmpty() {
        Browser browser = Application.statelessBrowser();
        browser.open("/zombie/admin/users/create");

        WebElement usernameElement = browser.findElement(By.id("username"));
        usernameElement.sendKeys("");

        WebElement passwordElement = browser.findElement(By.id("password"));
        passwordElement.sendKeys("");

        WebElement nameElement = browser.findElement(By.id("name"));
        nameElement.sendKeys("");

        WebElement emailElement = browser.findElement(By.id("email"));
        emailElement.sendKeys("");

        WebElement saveElement = browser.findElement(By.id("submit"));
        saveElement.click();

        WebElement messageElement = browser.findElement(By.name("error_message_div"));

        assertThat(messageElement.getText(), is("All fields are mandatory."));
    }

    @Test
    public void shouldDisplayRespectiveErrorMessagesWhenAnyFieldsAreInvalid() {
        Browser browser = Application.statelessBrowser();
        browser.open("/zombie/admin/users/create");

        WebElement usernameElement = browser.findElement(By.id("username"));
        usernameElement.sendKeys("pass");

        WebElement passwordElement = browser.findElement(By.id("password"));
        passwordElement.sendKeys("pass w");

        WebElement nameElement = browser.findElement(By.id("name"));
        nameElement.sendKeys("qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg");

        WebElement emailElement = browser.findElement(By.id("email"));
        emailElement.sendKeys("ndkdnkn@dlm");

        WebElement saveElement = browser.findElement(By.id("submit"));
        saveElement.click();

        WebElement invalidUserNameElement = browser.findElement(By.id("invalid_user_name"));
        assertThat(invalidUserNameElement.getText(), is("The Username must have 5-40 alphanumeric characters and no whitespaces."));

        WebElement invalidPasswordElement = browser.findElement(By.id("invalid_password"));
        assertThat(invalidPasswordElement.getText(), is("The password must have 6-40 alphanumeric characters."));

        WebElement invalidNameElement = browser.findElement(By.id("invalid_name"));
        assertThat(invalidNameElement.getText(), is("The name should not exceed 40 characters."));

        WebElement invalidEmailElement = browser.findElement(By.id("invalid_email"));
        assertThat(invalidEmailElement.getText(), is("The email address is not valid (For eg: email@site.com)."));
    }

    @Test
    public void shouldSaveNewUserWhenAllFieldsAreValid() {
        Browser browser = Application.statelessBrowser();
        browser.open("/zombie/admin/users/create");

        WebElement usernameElement = browser.findElement(By.id("username"));
        usernameElement.sendKeys("username");

        WebElement passwordElement = browser.findElement(By.id("password"));
        passwordElement.sendKeys("password123");

        WebElement nameElement = browser.findElement(By.id("name"));
        nameElement.sendKeys("yahya");

        WebElement emailElement = browser.findElement(By.id("email"));
        emailElement.sendKeys("email@email.com");

        WebElement saveElement = browser.findElement(By.id("submit"));
        saveElement.click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));

        WebElement userListElement = browser.findElement(By.id("username_value_2"));
        assertThat(userListElement.getText(), is(equalTo("yahya")));

    }

    @Test
    public void shouldGoToUserListPageWhenClickedOkOnAlertBox() {

        WebServer webServer = new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/admin/users/create");
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : Create User"));

            WebElement cancel = webDriver.findElement(By.name("cancel"));
            cancel.click();
            Alert alert = webDriver.switchTo().alert();
            alert.accept();
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : User List"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webServer.stop();
            webDriver.close();
        }
    }

    @Test
    public void shouldRemainOnCreateUserPageWhenClickedCancelOnAlertBox() {

        WebServer webServer = new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/admin/users/create");
            Assert.assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : Create User"));

            WebElement userNameElement = webDriver.findElement(By.id("username"));
            userNameElement.sendKeys("username");

            WebElement cancel = webDriver.findElement(By.name("cancel"));
            cancel.click();
            Alert alert = webDriver.switchTo().alert();
            alert.dismiss();
            assertThat(webDriver.getTitle(), CoreMatchers.is("Zombie Dash : Create User"));

            assertThat(userNameElement.getAttribute("value"), is(equalTo("username")));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            webServer.stop();
            webDriver.close();
        }
    }

    @Test
    public void shouldDisplayErrorMessageWhenUserNameAlreadyExists() {
        Browser browser = Application.statelessBrowser();
        browser.open("/zombie/admin/users/create");

        WebElement usernameElement = browser.findElement(By.id("username"));
        usernameElement.sendKeys("admin");

        WebElement passwordElement = browser.findElement(By.id("password"));
        passwordElement.sendKeys("password123");

        WebElement nameElement = browser.findElement(By.id("name"));
        nameElement.sendKeys("yahya");

        WebElement emailElement = browser.findElement(By.id("email"));
        emailElement.sendKeys("email@email.com");

        WebElement saveElement = browser.findElement(By.id("submit"));
        saveElement.click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));

        WebElement messageElement = browser.findElement(By.name("error_message_div"));
        assertThat(messageElement.getText(), is("Conflicting username, enter something else."));
    }
}
