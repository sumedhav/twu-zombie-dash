package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.UserManager;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ListUsersTest extends BasePageTest {
    private JdbcTemplate jdbcTemplate;
    @Before
    public void setupSession()
    {
        browser= BrowserSessionBuilder
                .aBrowserSession()
                .usingHttps()
                .loggedInAsAdmin()
                .build()
                .open("/app/zombie/admin/users/list");
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
    public void shouldGoToUserDetailsPageOnClickingThatUserLink() throws Exception {
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        UserManager userManager = new UserManager(jdbcTemplate,"username");
        userManager.insertUser();

        setupSession();

        String nameOfSelectedUser = browser.findElement(By.id("username_value_1")).getText();
        browser.clickOn("username_value_1");
        assertThat(browser.getPageTitle(), is("Zombie Dash : User Details"));
        assertThat(browser.findElement(By.id("name_value")).getText(), is(equalTo(nameOfSelectedUser)));
        userManager.deleteUser();
    }


}
