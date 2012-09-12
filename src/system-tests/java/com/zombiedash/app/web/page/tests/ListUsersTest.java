package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.UserTestDataManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ListUsersTest extends BasePageTest {
    private JdbcTemplate jdbcTemplate;
    private UserTestDataManager userTestDataManager;

    @Before
    public void setUp()
    {
        browser = BrowserSessionBuilder.buildHttpsAdminSession()
                .open("/app/zombie/admin/users/list");
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        userTestDataManager = new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
    }

    @Test
    public void shouldGoToCreateUserPageWhenClickedOnCreateNewUserButton() throws Exception {
        browser.clickOn("create_user");
        assertThat(browser.getPageTitle(), is("Zombie Dash : Create User"));
    }

    @Test
    public void shouldGoToHomePageWhenClickedOnBackButton() throws Exception {
        browser.clickOn("back_user_home");
        assertThat(browser.getPageTitle(), is("Zombie Dash : Welcome"));
    }

    @Test
    @Rollback(true)
    public void shouldGoToUserDetailsPageOnClickingThatUserLink() throws Exception {
        userTestDataManager.insertUserWithUsername("username");
        browser.refresh();
        String nameOfSelectedUser = browser.findElement(By.id("username_value_1")).getText();
        browser.clickOn("username_value_1");
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));
        assertThat(browser.findElement(By.id("name_value")).getText(), is(equalTo(nameOfSelectedUser)));
    }


}
