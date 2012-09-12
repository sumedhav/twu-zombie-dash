package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class CreateUserPageTest extends BasePageTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        browser = BrowserSessionBuilder.buildHttpsAdminSession().open("/app/zombie/admin/user/create");
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        jdbcTemplate.execute("DELETE from zombie_users WHERE username!='admin'");
    }

    @Test
    public void shouldDisplayErrorMessageWhenAnyFieldIsEmpty() {
        browser.clickOn("submit");
        WebElement messageElement = browser.findElement(By.id("username_field_empty"));
        assertThat(messageElement.getText(), is("You can't leave this field empty."));
    }

    @Test
    public void shouldDisplayRespectiveErrorMessagesWhenAnyFieldsAreInvalid() {

        browser.inputTextOn("username", "pass")
                .inputTextOn("password", "pass w")
                .inputTextOn("name", "qwertyuioplkjhgfdsazxcvbnmqwertyuioplkjhg")
                .inputTextOn("email", "ndkdnkn@dlm").clickOn("submit");


        assertThat(browser.getTextById("invalid_user_name"), is("Username must have 5-40 alphanumeric characters and no whitespaces."));

        assertThat(browser.getTextById("invalid_password"), is("Password must have 6-40 alphanumeric characters with at least one digit(s)."));

        assertThat(browser.getTextById("invalid_name"), is("Name should not exceed 40 characters and should not contain digits, special characters."));

        assertThat(browser.getTextById("invalid_email"), is("Please enter a valid email address."));
    }

    @Test
    @Rollback(true)
    public void shouldSaveNewUserWhenAllFieldsAreValid() {
        browser.inputTextOn("username", "username")
                .inputTextOn("password", "password123")
                .inputTextOn("name", "yahya")
                .inputTextOn("email", "email@email.com").clickOn("submit");

        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));

        assertThat(browser.getTextById("username_value_1"), is(equalTo("yahya")));
    }

    @Test
    public void shouldGoToUserListPageWhenClickedOkOnAlertBox() {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .withJavascriptEnabled()
                .usingHttps()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
        browser.clickOn("cancel").alertOk();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

    @Test
    @Rollback(true)
    public void shouldRemainOnCreateUserPageWhenClickedCancelOnAlertBox() {
        browser= BrowserSessionBuilder
                .aBrowserSession()
                .withJavascriptEnabled()
                .usingHttps()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
        browser.clickOn("cancel").alertCancel();
        assertThat(browser.getPageTitle(), CoreMatchers.is("Zombie Dash : Create User"));
    }

    @Test
    public void shouldDisplayErrorMessageWhenUserNameAlreadyExists() {
        browser.inputTextOn("username", "admin")
                .inputTextOn("password", "password123")
                .inputTextOn("name", "yahya")
                .inputTextOn("email", "email@email.com").clickOn("submit");

        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));

        assertThat(browser.getTextByName("error_message_div"), is("Someone already has that username. Try another."));
    }

}
