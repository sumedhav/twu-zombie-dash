package com.zombiedash.app.web.page.tests;


import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import com.zombiedash.app.web.page.tests.helper.TriviaGameTestDataCreationTemplate;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

        initializeQuestionsAndOptions();

        browser.open("/zombie/conference/user/game");
        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        List<WebElement> elements = browser.findElements(By.cssSelector("h3"));
        assertThat(elements.size(), is(2));
        assertThat(elements.get(0).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(1).getText(), equalTo("Is it lunch time?"));
    }

    private void initializeQuestionsAndOptions() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        testDataTemplate = new TriviaGameTestDataCreationTemplate(jdbcTemplate);

        testDataTemplate.givenAQuestionWith(1, "Where is Red Fort");
        testDataTemplate.givenAnOptionFor(1, 1, "Delhi", true);
        testDataTemplate.givenAnOptionFor(1, 2, "Paris", false);
        testDataTemplate.givenAnOptionFor(1, 3, "New York", false);

        testDataTemplate.givenAQuestionWith(2, "Is it lunch time?");
        testDataTemplate.givenAnOptionFor(2, 4, "I bet it is", true);
        testDataTemplate.givenAnOptionFor(2, 5, "No thanks, fasting at the moment", false);
    }



}
