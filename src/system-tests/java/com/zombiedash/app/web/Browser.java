package com.zombiedash.app.web;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
            this.driver = new FirefoxDriver();
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

    public WebElement findElement(final By selector) {
      return new WebDriverWait(driver, 5).until(new ExpectedCondition<WebElement>(){
        @Override
        public WebElement apply(@Nullable WebDriver webDriver) {
          return driver.findElement(selector);
        }
      });
    }

    public Browser inputTextOn(String id, String text) {
        driver.findElement(By.id(id)).sendKeys(text);
        return this;
    }

    public Browser clickOn(String id) {
        driver.findElement(By.id(id)).click();
        return this;
    }

    public List<WebElement> findElements(final By selector) {
      return new WebDriverWait(driver, 5).until(new ExpectedCondition<List<WebElement>>() {
        @Override
        public List<WebElement> apply(@Nullable WebDriver webDriver) {
          return driver.findElements(selector);
        }
      });
    }

    public String getPageTitle() {
        return new WebDriverWait(driver, 5).until(new ExpectedCondition<String>() {
            @Override
            public String apply(@Nullable WebDriver webDriver) {
                return driver.findElement(By.tagName("title")).getText();
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

    public Browser alertOk() {
        this.driver.switchTo().alert().accept();
        return this;
    }

    public Browser alertCancel() {
        driver.switchTo().alert().dismiss();
        return this;
    }
}
