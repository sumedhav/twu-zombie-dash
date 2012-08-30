package com.zombiedash.app.web;


import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;

public class Browser {

    private final boolean javascriptEnabled;
    private final String hostAddress;
    private final WebDriver driver;

    public Browser(String hostAddress, boolean testWithFirefox) {
        this.hostAddress = hostAddress;
        if (testWithFirefox) {
            this.driver = (WebDriver) new FirefoxDriver();
            this.javascriptEnabled = true;
        } else {
            this.driver = new HtmlUnitDriver();
            this.javascriptEnabled = false;
        }
    }

    public Browser open(String url) {
        if (url.startsWith("/")) {
            driver.get(hostAddress + url);
        } else {
            driver.get(url);
        }
        return this;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isJavascriptEnabled() {
        return javascriptEnabled;
    }

    public WebElement findElement(final By selector) {
      return new WebDriverWait(driver, 5).until(new Function<WebDriver, WebElement>() {
        @Override
        public WebElement apply(@Nullable WebDriver webDriver) {
          return driver.findElement(selector);
        }
      });
    }

    public List<WebElement> findElements(final By selector) {
      return new WebDriverWait(driver, 5).until(new Function<WebDriver, List<WebElement>>() {
        @Override
        public List<WebElement> apply(@Nullable WebDriver webDriver) {
          return driver.findElements(selector);
        }
      });
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public String getBodyClass() {
        return new WebDriverWait(driver,5).until(new Function<WebDriver, String>() {
          @Override
          public String apply(@Nullable WebDriver webDriver) {
            return findElement(By.tagName("body")).getAttribute("class");
          }
        });
    }

    public void stop() {
        try {
            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }
}
