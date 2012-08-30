package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
@Ignore("WIP: need to implement common login behaviour")
public class UserDetailsPageTest extends BasePageTest {
    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/admin");

    }
    @Test
    public void shouldGoToUserDetailsPageOnClickingBack() throws Exception {
        browser= BrowserSessionBuilder
                .newStatelessSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/admin");

        browser.findElement(By.id("back_user_details"))
                .click();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

    @Test
    public void shouldRemainOnUserDetailsPageWhenClickedCancelOnAlertBox() {
        browser.findElement(By.id("delete_user"))
                .click();
        browser.alertCancel();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));

    }

    @Ignore()
    @Test
    public void shouldDeleteUserWhenClickedOkOnAlertBox() {
        browser=BrowserSessionBuilder
                .newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/user/create");
        browser.inputTextOn("username","username")
                .inputTextOn("password","password123")
                .inputTextOn("name","yahya")
                .inputTextOn("email","email@email.com")
                .clickOn("submit");
        setupSession();
        browser.clickOn("delete_user");
        browser.alertOk();
        Assert.assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }
}
