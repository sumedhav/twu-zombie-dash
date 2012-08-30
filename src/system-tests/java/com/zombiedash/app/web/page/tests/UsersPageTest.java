package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UsersPageTest {

    @Test
    public void shouldGoToCreateUserPageWhenClickedOnCreateNewUserButton() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/users");

        WebElement createUserElement = browser.findElement(By.id("create_user"));
        createUserElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));
    }

    @Ignore
    @Test
    public void shouldGoToHomePageWhenClickedOnBackButton() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/users");

        WebElement createUserElement = browser.findElement(By.id("back_user_home"));
        createUserElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
    }

    @Test
    public void shouldGoToUserDetailsPageOnClickingThatUserLink() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/admin/users");

        WebElement userElement = browser.findElement(By.id("username_value_1"));
        String nameOfSelectedUser = userElement.getText();
        userElement.click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));

        WebElement nameElement = browser.findElement(By.id("name_value"));
        assertThat(nameElement.getText(), is(equalTo(nameOfSelectedUser)));
    }


}