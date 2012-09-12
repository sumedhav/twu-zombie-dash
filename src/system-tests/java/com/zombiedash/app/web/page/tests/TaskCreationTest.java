package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.ConferenceHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TaskCreationTest extends BasePageTest{
  private JdbcTemplate jdbcTemplate;
  private ConferenceRepository conferenceRepository;

  @Before
  public void setUp() {
    browser = BrowserSessionBuilder
        .aBrowserSession()
        .withJavascriptEnabled()
        .usingHttps()
        .loggedInAsAdmin()
        .build();
  }

  @Test
  public void ShouldAddQuestionToTask() {
    jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
    ConferenceHelper.clearDatabase(jdbcTemplate);
    conferenceRepository = new ConferenceRepository(jdbcTemplate);
    UUID conferenceId = ConferenceHelper.insertSampleConference(conferenceRepository);
    browser.open("/app/zombie/gamedesigner/conference/" + conferenceId + "/create/task");
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Create Task")));
    browser.inputTextOn("task_name", "My Task Name")
        .inputTextOn("task_description", "My Task Description")
        .clickOn("addQuestion");
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Create Question")));
    browser.inputTextOn("question_text","My Question")
        .clickOn("addOption")
        .inputTextOn("optionText_1","First")
        .clickOn("addOption")
        .inputTextOn("optionText_2","Second")
        .clickOn("addOption")
        .inputTextOn("optionText_3","Third")
        .clickOn("isCorrect2")
        .clickOn("submitQuestion");
    assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Conference Information")));
  }

}

