package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


public class CreateUserPageTest {

    @Test
    public void shouldDisplayErrorMessageWhenAnyFieldIsEmpty() {
        Browser browser = Application.browser();
        browser.open("/app/zombie/admin/users/create");

        browser.findElement(By.id("username")).sendKeys("admin");
        browser.findElement(By.id("password")).sendKeys("Welcome1");
        browser.findElement(By.tagName("button")).click();

        browser.findElement(By.id("submit")).click();

        WebElement messageElement = browser.findElement(By.name("error_message_div"));

        assertThat(messageElement.getText(), is("All fields are mandatory."));
    }
}
