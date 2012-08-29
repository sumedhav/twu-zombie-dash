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
      //  assertThat(browser.getPageTitle(),is("Zombie Dash : Create User"));
    }
}
