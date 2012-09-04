package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.UserManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserDetailsPageTest extends BasePageTest {
    private JdbcTemplate jdbcTemplate;
    private UserManager userManager;

    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .newJavascriptEnabledSession()
                .loggedInAsAdmin()
                .build();
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        userManager = new UserManager(jdbcTemplate,"username1");
        userManager.insertUser();

        browser.open("/app/zombie/admin/user/view/username1");

    }
    @Test
    public void shouldGoToUserDetailsPageOnClickingBack() throws Exception {
        browser.clickOn("back_user_details");
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
        userManager.deleteUser();
    }

    @Test
    public void shouldRemainOnUserDetailsPageWhenClickedCancelOnAlertBox() {
        browser.clickOn("delete_user").alertCancel();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));
        userManager.deleteUser();
    }

    @Test
    public void shouldDeleteUserWhenClickedOkOnAlertBox() {
        browser.clickOn("delete_user").alertOk();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

}
