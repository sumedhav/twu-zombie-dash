package com.zombiedash.app.web.page.tests.helper;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest {
private WebDriver firefox;

  @Before
  public void setup() {
    firefox = new FirefoxDriver();
  }

  @Ignore
  @Test
  public void shouldFindGoogle() {
    firefox.get("http://www.google.com");
  }

  @After
  public void teardown() {
    firefox.close();
  }
}
