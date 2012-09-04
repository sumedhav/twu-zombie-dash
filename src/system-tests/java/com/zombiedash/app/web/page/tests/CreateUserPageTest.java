package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.jetty.WebServer;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
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


public class CreateUserPageTest extends BasePageTest {

    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .newStatelessSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
    }

    @Test
    public void shouldDisplayErrorMessageWhenAnyFieldIsEmpty() {
        browser.clickOn("submit");

        WebElement messageElement = browser.findElement(By.id("username_field_empty"));
        assertThat(messageElement.getText(), is("You can't leave this field empty."));
    }

    @Test
    public void shouldDisplayRespectiveErrorMessagesWhenAnyFieldsAreInvalid() {

        browser.inputTextOn("username","pass")
                .inputTextOn("password","pass w")
                .inputTextOn("name","qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg")
                .inputTextOn("email","ndkdnkn@dlm").clickOn("submit");


        WebElement invalidUserNameElement = browser.findElement(By.id("invalid_user_name"));
        assertThat(invalidUserNameElement.getText(), is("Username must have 5-40 alphanumeric characters and no whitespaces."));

        WebElement invalidPasswordElement = browser.findElement(By.id("invalid_password"));
        assertThat(invalidPasswordElement.getText(), is("Password must have 6-40 characters, at least one digit(s) and no non-alphanumeric characters."));

        WebElement invalidNameElement = browser.findElement(By.id("invalid_name"));
        assertThat(invalidNameElement.getText(), is("Name should not exceed 40 characters and should not contain digits, special characters."));

        WebElement invalidEmailElement = browser.findElement(By.id("invalid_email"));
        assertThat(invalidEmailElement.getText(), is("Please enter a valid email address."));
    }

    @Test
    public void shouldSaveNewUserWhenAllFieldsAreValid() {
        browser.inputTextOn("username","username")
                .inputTextOn("password","password123")
                .inputTextOn("name","yahya")
                .inputTextOn("email","email@email.com").clickOn("submit");


        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));

        WebElement userListElement = browser.findElement(By.id("username_value_2"));
        assertThat(userListElement.getText(), is(equalTo("yahya")));

    }

    @Test
    public void shouldGoToUserListPageWhenClickedOkOnAlertBox() {
        browser= BrowserSessionBuilder
                .newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
        browser.clickOn("cancel").alertOk();
        assertThat(browser.getPageTitle(),is("Zombie Dash : User List"));
    }

    @Test
    public void shouldRemainOnCreateUserPageWhenClickedCancelOnAlertBox() {
        browser= BrowserSessionBuilder
                .newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
        browser.clickOn("cancel").alertCancel();
        assertThat(browser.getPageTitle(), CoreMatchers.is("Zombie Dash : Create User"));


    }

    @Test
    public void shouldDisplayErrorMessageWhenUserNameAlreadyExists() {
        browser.inputTextOn("username","admin")
                .inputTextOn("password","password123")
                .inputTextOn("name","yahya")
                .inputTextOn("email","email@email.com").clickOn("submit");

        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));

        WebElement messageElement = browser.findElement(By.name("error_message_div"));
        assertThat(messageElement.getText(), is("Someone already has that username. Try another."));
    }
}
