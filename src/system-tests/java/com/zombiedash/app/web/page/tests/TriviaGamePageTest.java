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
        assertThat(elements.get(existingQnCount).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(existingQnCount + 1).getText(), equalTo("Is it lunch time?"));
    }


    @Test
    public void shouldGoTOHomePageWhenClickedOkOnAlertBox() {

        WebServer webServer=new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/conference/user/game");
            assertThat(webDriver.getTitle(), is("Welcome to Trivia Game!"));

            WebElement cancel = webDriver.findElement(By.name("cancel"));
            cancel.click();
            Alert alert=webDriver.switchTo().alert();
            alert.accept();
            Thread.sleep(3000);
            assertThat(webDriver.getTitle(), is("Customer Home"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            webServer.stop();
            webDriver.close();
        }
    }

    @Test
    public void shouldStayOnSamePageIfCancelIsClickedOnAlertBox() {

        WebServer webServer=new WebServer(1234);
        WebDriver webDriver = new FirefoxDriver();
        try {
            webServer.start();
            webDriver.get("http://localhost:1234/zombie/conference/user/game");
            assertThat(webDriver.getTitle(), is("Welcome to Trivia Game!"));

            WebElement cancel = webDriver.findElement(By.name("cancel"));
            cancel.click();
            Alert alert=webDriver.switchTo().alert();
            alert.dismiss();
            Thread.sleep(3000);
            assertThat(webDriver.getTitle(), is("Welcome to Trivia Game!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            webServer.stop();
            webDriver.close();
        }
    }

    @Test
    public void shouldAllowOnlyOneAnswerForAQuestion() throws Exception {
        Browser browser = Application.browser();
        browser.open("/zombie/conference/user/game");
        List<WebElement> radioButtons = browser.findElements(By.name("question_1"));
        for (WebElement radioButton : radioButtons) {
            radioButton.click();
        }
        int numberOfSelections = 0;
        for (WebElement radioButton : radioButtons) {
            if(radioButton.isSelected()){
                numberOfSelections++;
            }
        }
        assertThat(numberOfSelections, is(1));

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
