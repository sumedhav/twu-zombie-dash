package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateConferencePageTest {
    Browser browser;
    @Before
     public void adminLogsIn(){
       browser = Application.javascriptEnabledBrowser(true);
       browser.open("/app/zombie/login");
       browser.inputTextOn("username","admin");
       browser.inputTextOn("password","Welcome1");
       browser.findElement(By.tagName("button")).click();
    }
    @Test
    public void clickOnCreateConferenceShouldOpenCreateConferencePage(){
        browser.open("/app/zombie/admin/conference/list");
        browser.clickOn("conference_creation");
        assertThat(browser.getCurrentUrl(),endsWith("/app/zombie/admin/conference/create"));
   }
}
