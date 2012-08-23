package com.zombiedash.app.web.page.tests;


import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.Browser;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TriviaGamePageTest {

    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldDisplayGameQuestions() {

        Browser browser = Application.browser();

        //initializeQuestions();

        browser.open("/zombie/conference/user/game");
        assertThat(browser.getPageTitle(), is("Welcome to Trivia Game!"));

        List<WebElement> elements = browser.findElements(By.cssSelector("h3"));
        assertThat(elements.size(), is(2));
        assertThat(elements.get(0).getText(), equalTo("Where is Red Fort"));
        assertThat(elements.get(1).getText(), equalTo("Is it lunch time?"));
    }

    private void initializeQuestions() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db1");
        dataSource.setUsername("sa");

        jdbcTemplate = new JdbcTemplate(dataSource);
        givenAQuestionWith(11, "Where is Red Fort");
        givenAnOptionFor(11, 1, "Delhi", true);
        givenAnOptionFor(11, 2, "Paris", false);
        givenAnOptionFor(11, 3, "New York", false);
        givenAQuestionWith(21, "Is it lunch time?");
        givenAnOptionFor(21, 4, "I bet it is", true);
        givenAnOptionFor(21, 5, "No thanks, fasting at the moment", false);
    }

    private void givenAQuestionWith(int id, String text) {
        jdbcTemplate.execute(String.format(
                "insert into Question (ID,Text) values(%d, '%s')", id, text));
    }

    private void givenAnOptionFor(int questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.execute(String.format("insert into Option (id,question_id,text,correct) " +
                "values (%d, %d, '%s', %b)",
                optionId, questionId, text, correct));
    }

}
