package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class LoginPageTest {

    @Test
    public void shouldGoToAdminLoginSuccessPageIfUserIsAdmin() throws Exception {
        Browser browser = Application.browser();
        browser.open("http://www.google.com");

//        WebElement usernameElement = browser.findElement(By.name("Username"));
//        usernameElement.sendKeys("admin");
//
//        WebElement passwordElement = browser.findElement(By.name("Password"));
//        passwordElement.sendKeys("Welcome1");
//
//        WebElement submitElement = browser.findElement(By.name("Submit"));
//        submitElement.click();
//
//        assertThat(browser.getCurrentUrl(), is("/zombie/login/Authenticate"));
    }
}
