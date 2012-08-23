package com.example.app.web.page.tests;

import com.zombiedash.app.web.Browser;
import org.junit.Rule;
import org.junit.Test;

public class ExampleFunctionalTests {

    @Rule
    public LoginLogoutRule rule = new LoginLogoutRule("test");

    @Test
    public void shouldSearchForSomething() {
        Browser browser = rule.browser().open("/app");
    }

}
