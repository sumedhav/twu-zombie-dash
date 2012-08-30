package com.zombiedash.app.web.page.tests;


import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.TriviaGameTestDataCreationTemplate;
import org.hamcrest.core.IsNot;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

//@Ignore("WIP: need to implement common login behaviour")
public class TriviaGamePageTest extends BasePageTest {

    private TriviaGameTestDataCreationTemplate testDataTemplate;

    @Test
    public void shouldDisplayGameQuestions() {

        browser = BrowserSessionBuilder.newStatelessSession().build();

        int existingQnCount = getNumberOfExistingQuestionsInDatabase();
        initializeQuestionsAndOptions();

        browser.open("/app/zombie/conference/user/game");
        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        List<WebElement> elements = browser.findElements(By.cssSelector(".question"));
        assertThat(elements.size(), is(existingQnCount + 2));
        assertThat(elements.get(existingQnCount).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(existingQnCount + 1).getText(), equalTo("Is it lunch time?"));
    }


    @Test
    public void shouldGoTOHomePageWhenClickedOkOnAlertBox() throws Exception {

        browser = BrowserSessionBuilder.newJavascriptEnabledSession().build();
        browser.open("/app/zombie/conference/user/game");

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        WebElement cancel = browser.findElement(By.name("cancel"));
        cancel.click();
        browser.alertOk();
        assertThat(browser.getPageTitle(), is("Customer Home"));
    }

    @Test
    public void shouldStayOnSamePageIfCancelIsClickedOnAlertBox() throws Exception {

        browser = BrowserSessionBuilder.newJavascriptEnabledSession().build();
        browser.open("/app/zombie/conference/user/game");

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        WebElement optionElement = browser.findElement(By.id("option_1_1"));
        optionElement.click();
        WebElement cancel = browser.findElement(By.name("cancel"));
        cancel.click();
        browser.alertCancel();

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        assertThat(optionElement.isSelected(), is(true));
    }

    @Test
    public void shouldDisplayResultPageWithScoreWhenAllQuestionsAreAnsweredAndSubmitted() throws Exception {
        browser = BrowserSessionBuilder.newJavascriptEnabledSession().build();
        browser.open("/app/zombie/conference/user/game");

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        List<WebElement> questions = browser.findElements(By.className("question"));
        for (int questionNumber = 1; questionNumber <= questions.size(); questionNumber++) {
            List<WebElement> options = browser.findElements(By.name("question_" + questionNumber));
            options.get(0).click();
        }
        WebElement submitButton = browser.findElement(By.id("submit_button"));
        submitButton.click();

        assertThat(browser.getPageTitle(), is("Results Page"));
        assertThat(browser.findElement(By.id("obtainedScore")).getText(), IsNot.not(""));
        assertThat(browser.findElement(By.id("maxScore")).getText(), IsNot.not(""));
    }

    @Test
    public void shouldDisplayAlertWhenNotAllQuestionsAreAnsweredAndStayOnSamePage() throws Exception {
        browser = BrowserSessionBuilder.newJavascriptEnabledSession().build();
        browser.open("/app/zombie/conference/user/game");

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        List<WebElement> questions = browser.findElements(By.className("question"));
        for (int questionNumber = 1; questionNumber < questions.size(); questionNumber++) {
            List<WebElement> options = browser.findElements(By.name("question_" + questionNumber));
            options.get(0).click();
        }
        WebElement submitButton = browser.findElement(By.id("submit_button"));
        submitButton.click();
        browser.alertOk();
        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        for (int questionNumber = 1; questionNumber < questions.size(); questionNumber++) {
            List<WebElement> options = browser.findElements(By.name("question_" + questionNumber));
            assertThat(options.get(0).isSelected(),is(true));
        }
        assertThat(browser.findElement(By.name("question_"+questions.size())).isSelected(),is(false));
    }

    @Test
    public void shouldAllowOnlyOneAnswerForAQuestion() throws Exception {
        browser = BrowserSessionBuilder.newStatelessSession().build();
        browser.open("/app/zombie/conference/user/game");
        List<WebElement> radioButtons = browser.findElements(By.name("question_1"));
        for (WebElement radioButton : radioButtons) {
            radioButton.click();
        }
        int numberOfSelections = 0;
        for (WebElement radioButton : radioButtons) {
            if (radioButton.isSelected()) {
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
