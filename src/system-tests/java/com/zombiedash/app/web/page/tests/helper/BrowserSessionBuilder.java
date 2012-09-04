package com.zombiedash.app.web.page.tests.helper;

import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.openqa.selenium.By;

public class BrowserSessionBuilder {
    private boolean stateless = true;
    private boolean httpsSession = false;
    private Browser browser;
    private String user;
    private String password;

    private BrowserSessionBuilder() {

    }

    public BrowserSessionBuilder loggedInAsAdmin() {
        return loggedInAs("admin", "Welcome1");
    }

    public BrowserSessionBuilder loggedInAs(String user, String password) {
        this.user = user;
        this.password = password;
        return this;
    }

    private void login(String user, String password) {
        browser.open("/app/zombie/login");
        browser.findElement(By.name("j_username")).sendKeys(user);
        browser.findElement(By.name("j_password")).sendKeys(password);
        browser.findElement(By.tagName("button")).click();
    }

    public Browser build() {
        browser = stateless ? Application.statelessBrowser(httpsSession) : Application.javascriptEnabledBrowser(httpsSession);
        if (user != null && password != null) {
            login(user, password);
        }
        return browser;
    }

    public BrowserSessionBuilder withJavascriptEnabled() {
        this.stateless = false;
        return this;
    }

    public BrowserSessionBuilder usingHttps() {
        this.httpsSession = true;
        return this;
    }

    public static BrowserSessionBuilder aBrowserSession() {
        return new BrowserSessionBuilder();
    }
}
