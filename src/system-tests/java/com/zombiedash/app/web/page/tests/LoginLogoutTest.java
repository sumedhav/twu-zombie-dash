package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginLogoutTest {

    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        Browser browser = Application.browser();
        browser.open("/app/zombie/login/LoginForm");

        browser.findElement(By.name("j_username")).sendKeys("admin");

        browser.findElement(By.name("j_password")).sendKeys("Welcome1");

        browser.findElement(By.tagName("button")).click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Welcome"));

        //todo: move this into ANOTHER test
//        browser.findElement(By.id("Logout")).click();
//
//        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
//
//        assertThat(browser.findElement(By.id("message_to_be_displayed")).getText(), is("You have been logged out successfully!!"));

    }

    @Test
    @Ignore("WIP: logout behaviour changed with Spring security")
    public void shouldStayInLoginFormIfLoginUnsuccessful() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/login/LoginForm");

        browser.findElement(By.name("username")).sendKeys("admin1");

        browser.findElement(By.name("password")).sendKeys("12Welcome1");

        browser.findElement(By.tagName("button")).click();

        assertThat(browser.getPageTitle(), is("Zombie Dash : Login"));
    }
}
