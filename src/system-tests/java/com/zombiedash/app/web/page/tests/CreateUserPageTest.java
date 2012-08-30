package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class CreateUserPageTest extends BasePageTest {
    @Before
    public void startSession() {
        browser = BrowserSessionBuilder.newStatelessSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
    }
    @Test
    public void shouldDisplayErrorMessageWhenAnyFieldIsEmpty() {
        browser.findElement(By.id("submit")).click();

        WebElement messageElement = browser.findElement(By.name("error_message_div"));

        assertThat(messageElement.getText(), is("All fields are mandatory."));
    }

    @Test
    public void shouldDisplayRespectiveErrorMessagesWhenAnyFieldsAreInvalid() {
        browser.inputTextOn("username", "pass")
                .inputTextOn("password", "pass w")
                .inputTextOn("name", "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg")
                .inputTextOn("email", "ndkdnkn@dlm")
                .clickOn("submit");

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
        browser.inputTextOn("username", "username")
                .inputTextOn("password", "password123")
                .inputTextOn("name", "yahya")
                .inputTextOn("email", "email@email.com")
                .clickOn("submit");

        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));

        WebElement userListElement = browser.findElement(By.id("username_value_2"));
        assertThat(userListElement.getText(), is(equalTo("yahya")));
    }

    @Test
    public void shouldGoToUserListPageWhenClickedOkOnAlertBox() {
        browser = BrowserSessionBuilder.newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");

        browser.findElement(By.name("cancel")).click();
        browser.alertOk();

        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

    @Test
    public void shouldRemainOnCreateUserPageWhenClickedCancelOnAlertBox() {
        browser = BrowserSessionBuilder.newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");

        browser.findElement(By.name("cancel")).click();
        browser.alertCancel();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));
    }

    @Test
    public void shouldDisplayErrorMessageWhenUserNameAlreadyExists() {
        browser.inputTextOn("username", "admin")
                .inputTextOn("password", "password123")
                .inputTextOn("name", "yahya")
                .inputTextOn("email", "email@email.com")
                .clickOn("submit");

        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));

        WebElement messageElement = browser.findElement(By.name("error_message_div"));
        assertThat(messageElement.getText(), is("Conflicting username, enter something else."));
    }
}
