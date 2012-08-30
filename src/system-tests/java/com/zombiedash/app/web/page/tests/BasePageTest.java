package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.web.Browser;
import org.junit.After;

public class BasePageTest {
    protected Browser browser;

    @After
    public void closeBrowser() {
        browser.stop();
    }
}
