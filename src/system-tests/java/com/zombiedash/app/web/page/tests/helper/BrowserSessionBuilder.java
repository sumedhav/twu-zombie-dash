package com.zombiedash.app.web.page.tests.helper;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.openqa.selenium.By;

public class BrowserSessionBuilder {
    private boolean stateless = true;
    private Browser browser;

    private BrowserSessionBuilder(boolean stateless) {
        browser = stateless ? Application.statelessBrowser() : Application.javascriptEnabledBrowser();
    }

    public BrowserSessionBuilder loggedInAsAdmin() {
        return loggedInAs("admin", "Welcome1");
    }

    public BrowserSessionBuilder loggedInAs(String user, String password) {
        browser.open("/app/zombie/login/LoginForm");
        browser.findElement(By.name("j_username")).sendKeys(user);
        browser.findElement(By.name("j_password")).sendKeys(password);
        browser.findElement(By.tagName("button")).click();
        return this;
    }

    public Browser build() {
        return browser;
    }

    public static BrowserSessionBuilder newStatelessSession() {
        return new BrowserSessionBuilder(true);
    }

    public static BrowserSessionBuilder newJavascriptEnabledSession() {
        return new BrowserSessionBuilder(false);
    }

    private Browser getBrowser() {
        return stateless ? Application.statelessBrowser() : Application.javascriptEnabledBrowser();
    }
}
