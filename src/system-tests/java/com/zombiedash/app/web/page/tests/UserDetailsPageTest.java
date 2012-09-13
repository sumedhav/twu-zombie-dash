package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.UserTestDataManager;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserDetailsPageTest extends BasePageTest {
    private JdbcTemplate jdbcTemplate;
    private UserTestDataManager userTestDataManager;
    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .aBrowserSession()
                .usingHttps()
                .withJavascriptEnabled()
                .loggedInAsAdmin()
                .build();
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        userTestDataManager =new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
        userTestDataManager.insertUserWithUsername("username1");

        browser.open("/app/zombie/admin/user/view/username1");

    }

    @Test
    @Rollback(true)
    public void shouldGoToUserDetailsPageOnClickingBack() throws Exception {
        browser.clickOn("back_user_details");
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

    @Test
    @Rollback(true)
    public void shouldRemainOnUserDetailsPageWhenClickedCancelOnAlertBox() {
        browser.clickOn("delete_user").alertCancel();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));
    }

    @Test
    @Rollback(true)
    public void shouldDeleteUserWhenClickedOkOnAlertBox() {
        browser.clickOn("delete_user").alertOk();
        assertThat(browser.getPageTitle(), is("Zombie Dash : User List"));
    }

}
