package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.repository.UserRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UsersPageTest extends BasePageTest {

    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .newStatelessSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/users/list");
    }
    @Test
    public void shouldGoToCreateUserPageWhenClickedOnCreateNewUserButton() throws Exception {

        browser.findElement(By.id("create_user"))
                .click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));
    }

    @Test
    public void shouldGoToHomePageWhenClickedOnBackButton() throws Exception {
        browser.findElement(By.id("back_user_home"))
                .click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Welcome"));
    }

    @Test
    public void shouldGoToUserDetailsPageOnClickingThatUserLink() throws Exception {
        browser= BrowserSessionBuilder
                .newStatelessSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");

        browser.inputTextOn("username","username")
                .inputTextOn("password","password123")
                .inputTextOn("name","yahya")
                .inputTextOn("email","email@email.com").clickOn("submit");

        String nameOfSelectedUser = browser.findElement(By.id("username_value_2")).getText();
        browser.findElement(By.id("username_value_2")).click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));
        assertThat(browser.findElement(By.id("name_value")).getText(), is(equalTo(nameOfSelectedUser)));
    }

}
