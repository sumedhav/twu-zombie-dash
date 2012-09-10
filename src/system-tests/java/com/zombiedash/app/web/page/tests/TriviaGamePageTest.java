package com.zombiedash.app.web.page.tests;


import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.TriviaGameTestDataCreationTemplate;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TriviaGamePageTest extends BasePageTest {

    public static final String TRIVIA_GAME_URL = "/app/zombie/conference/user/game";
    private TriviaGameTestDataCreationTemplate testDataTemplate;

    @Test
    public void shouldDisplayGameQuestions() {
        initializeStatelessBrowserAndSetUpData();

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        List<WebElement> elements = browser.findElements(By.cssSelector(".question"));
        assertThat(elements.size(), is(2));
        assertThat(elements.get(0).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(1).getText(), equalTo("Is it lunch time?"));
    }

    @Test
    public void shouldGoTOHomePageWhenClickedOkOnAlertBox() throws Exception {
        initializeJavaScriptBrowserAndSetUpData();
        browser.clickOn("cancel_button")
                .alertOk();

        assertThat(browser.getPageTitle(), is("Attendee Home"));
    }

    @Test
    public void shouldStayOnSamePageIfCancelIsClickedOnAlertBox() throws Exception {

        initializeJavaScriptBrowserAndSetUpData();

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        browser.clickOn("option_1_1")
                .clickOn("cancel_button")
                .alertCancel();

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        assertThat(browser.findElement(By.id("option_1_1")).isSelected(), is(true));
    }

    @Test
    public void shouldDisplayErrorMessageWhenNotAllQuestionsAreAnsweredAndStayOnSamePage() throws Exception {
        initializeJavaScriptBrowserAndSetUpData();

        browser.clickOn("submit_button");

        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));
        assertThat(browser.getTextById("incompleteQuestionsError"),is("You need to answer all the questions!"));

    }

    @Test
    public void shouldDisplayResultPageWithScoreWhenAllQuestionsAreAnsweredAndSubmitted() throws Exception {
        initializeJavaScriptBrowserAndSetUpData();

        List<WebElement> questions = browser.findElements(By.className("question"));
        for (int questionNumber = 1; questionNumber <= questions.size(); questionNumber++) {
            List<WebElement> options = browser.findElements(By.name("question_" + questionNumber));
            options.get(0).click();
        }
        browser.clickOn("submit_button");

        assertThat(browser.getPageTitle(), is("Results Page"));
        assertThat(browser.findElement(By.id("obtainedScore")).getText(), is(String.valueOf(questions.size())));
        assertThat(browser.findElement(By.id("maxScore")).getText(), is(String.valueOf(questions.size())));
    }



    @Test
    public void shouldAllowOnlyOneAnswerForAQuestion() throws Exception {
        initializeStatelessBrowserAndSetUpData();

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

    private void initializeQuestionsAndOptionsInTheDatabase() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        testDataTemplate = new TriviaGameTestDataCreationTemplate(jdbcTemplate);

        testDataTemplate.clearTables();
        UUID questionID1 = UUID.randomUUID();
        UUID taskID = UUID.randomUUID();
        testDataTemplate.insertTask("charles_task", taskID);
        testDataTemplate.insertQuestion(questionID1, "Where is Red Fort", taskID);
        testDataTemplate.insertOption(questionID1, 1, "Delhi", true);
        testDataTemplate.insertOption(questionID1, 2, "Paris", false);
        testDataTemplate.insertOption(questionID1, 3, "New York", false);
        UUID questionID2 = UUID.randomUUID();
        testDataTemplate.insertQuestion(questionID2, "Is it lunch time?", taskID);
        testDataTemplate.insertOption(questionID2, 4, "I bet it is", true);
        testDataTemplate.insertOption(questionID2, 5, "No thanks, fasting at the moment", false);
    }


    private void initializeJavaScriptBrowserAndSetUpData() {
        browser = BrowserSessionBuilder.aBrowserSession().usingHttps().withJavascriptEnabled().build();
        initializeQuestionsAndOptionsInTheDatabase();
        browser.open(TRIVIA_GAME_URL);
    }

    private void initializeStatelessBrowserAndSetUpData() {
        browser = BrowserSessionBuilder.aBrowserSession().usingHttps().build();
        initializeQuestionsAndOptionsInTheDatabase();
        browser.open(TRIVIA_GAME_URL);
    }
}
