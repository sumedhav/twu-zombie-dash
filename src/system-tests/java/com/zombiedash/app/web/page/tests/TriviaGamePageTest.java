package com.zombiedash.app.web.page.tests;


import com.example.app.jetty.WebServer;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.TriviaGameTestDataCreationTemplate;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TriviaGamePageTest {

    private TriviaGameTestDataCreationTemplate testDataTemplate;

    @Test
    public void shouldDisplayGameQuestions() {

        Browser browser = Application.browser();

        int existingQnCount = getNumberOfExistingQuestionsInDatabase();
        initializeQuestionsAndOptions();

        browser.open("/zombie/conference/user/game");
        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        List<WebElement> elements = browser.findElements(By.cssSelector(".question"));
        assertThat(elements.size(), is(existingQnCount + 2));
        assertThat(elements.get(existingQnCount + 0).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(existingQnCount + 1).getText(), equalTo("Is it lunch time?"));
    }


    @Test
    public void shouldGoTOHomePageWhenClickedOkOnAlertBox() {

        WebServer webServer=new WebServer(1234);
        WebDriver webDriver =(WebDriver) new FirefoxDriver();
        try {
            webServer.start();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        webDriver.get("http://localhost:1234/zombie/conference/user/game");
        assertThat(webDriver.getTitle(), is("Welcome to Trivia Game!"));

        WebElement cancel = webDriver.findElement(By.name("cancel"));
        cancel.click();
        Alert alert=webDriver.switchTo().alert();
        alert.accept();
        assertThat(webDriver.getTitle(), is("Customer Home"));
        webServer.stop();
        webDriver.close();
    }

    private void initializeQuestionsAndOptions() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        testDataTemplate = new TriviaGameTestDataCreationTemplate(jdbcTemplate);

        testDataTemplate.givenAQuestionWith(100, "Where is Red Fort");
        testDataTemplate.givenAnOptionFor(100, 1, "Delhi", true);
        testDataTemplate.givenAnOptionFor(100, 2, "Paris", false);
        testDataTemplate.givenAnOptionFor(100, 3, "New York", false);

        testDataTemplate.givenAQuestionWith(200, "Is it lunch time?");
        testDataTemplate.givenAnOptionFor(200, 4, "I bet it is", true);
        testDataTemplate.givenAnOptionFor(200, 5, "No thanks, fasting at the moment", false);
    }

    private int getNumberOfExistingQuestionsInDatabase() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        return new TriviaGameTestDataCreationTemplate(jdbcTemplate).getNumberOfExistingQuestionsInDatabase();
    }
}
