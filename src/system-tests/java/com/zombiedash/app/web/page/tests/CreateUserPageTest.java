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
    }

    @Test
    public void shouldDisplayRespectiveErrorMessagesWhenAnyFieldsAreInactive() {
        Browser browser = Application.browser();
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
}
