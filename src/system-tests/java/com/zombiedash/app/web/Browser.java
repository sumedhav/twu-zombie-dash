package com.zombiedash.app.web;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;

public class Browser {

    private final boolean javascriptEnabled;
    private final String hostAddress;
    private WebDriver driver;

    private final static int TIMEOUT= 30;

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
      return new WebDriverWait(driver, TIMEOUT).until(new ExpectedCondition<WebElement>(){
        @Override
        public WebElement apply(@Nullable WebDriver webDriver) {
          return driver.findElement(selector);
        }
      });
    }

    public Browser inputTextOn(String id, String text) {
        this.findElement(By.id(id)).sendKeys(text);
        return this;
    }

    public Browser clearTextOn(String id) {
      this.findElement(By.id(id)).clear();
      return this;
    }

    public Browser selectFromDropDown(String id, String choice) {
        Select select = new Select(this.findElement(By.id(id)));
        select.selectByValue(choice);
        return this;
    }

    public String getTextByName(String name)
    {
        return this.findElement(By.name(name)).getText();
    }

    public String getTextById(String id)
    {
        return this.findElement(By.id(id)).getText();
    }

    public Browser clickOn(String id) {
        this.findElement(By.id(id)).click();
        return this;
    }

    public List<WebElement> findElements(final By selector) {
      return new WebDriverWait(driver, TIMEOUT).until(new ExpectedCondition<List<WebElement>>() {
        @Override
        public List<WebElement> apply(@Nullable WebDriver webDriver) {
          return driver.findElements(selector);
        }
      });
    }

    public String getPageTitle() {
        return this.findElement(By.tagName("title")).getText();
    }

    public String currentUrl() {
        return driver.getCurrentUrl();
    }

    public void stop() {
        try {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
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

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
