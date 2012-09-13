package com.zombiedash.app.web.page.tests;

import com.zombiedash.app.model.Role;
import com.zombiedash.app.repository.ConferenceRepository;
import com.zombiedash.app.web.Application;
import com.zombiedash.app.web.page.tests.helper.BrowserSessionBuilder;
import com.zombiedash.app.web.page.tests.helper.ConferenceHelper;
import com.zombiedash.app.web.page.tests.helper.UserTestDataManager;
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
    private UserTestDataManager userTestDataManager;

    @Before
    public void setUp() {
        browser = BrowserSessionBuilder
                .aBrowserSession()
                .withJavascriptEnabled()
                .usingHttps()
                .build();
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        userTestDataManager=new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
        userTestDataManager.insertUser("samplegamedesigner","password1", Role.GAME_DESIGNER,"Game Designer","gamedesigner@mail.com");
    }

    @Test
    public void ShouldAddQuestionToTask() {
        jdbcTemplate = new JdbcTemplate(Application.setupDataSource());
        ConferenceHelper.clearDatabase(jdbcTemplate);
        conferenceRepository = new ConferenceRepository(jdbcTemplate);
        UUID conferenceId = ConferenceHelper.insertSampleConference(conferenceRepository);
        browser.loginAs("samplegamedesigner","password1");
        browser.open("/app/zombie/gamedesigner/conference/" + conferenceId + "/create/task");
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Create Task")));
        browser.inputTextOn("task_name", "My Task Name")
                .inputTextOn("task_description", "My Task Description")
                .clickOn("addQuestion");
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Create Question")));
        fillOutQuestionForm("addAnotherQuestion");
        assertThat(browser.getPageTitle(), is(equalTo("Zombie Dash : Create Question")));
        fillOutQuestionForm("submitQuestion");
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Conference Tasks")));

    }

    private void fillOutQuestionForm(String whichbutton) {
        browser.inputTextOn("question_text","My Question")
                .clickOn("addOption")
                .inputTextOn("optionText_1","First")
                .clickOn("addOption")
                .inputTextOn("optionText_2","Second")
                .clickOn("addOption")
                .inputTextOn("optionText_3","Third")
                .clickOn("isCorrect2")
                .clickOn(whichbutton);
    }

    @Test
    public void shouldGoToConferenceAndTaskViewPageAfterClickingOnConference(){
        ConferenceHelper.clearDatabase(jdbcTemplate);
        conferenceRepository = new ConferenceRepository(jdbcTemplate);
        ConferenceHelper.insertSampleConference(conferenceRepository);
        browser.loginAs("samplegamedesigner","password1");
        browser.clickOn("existing_conference_id_1");
        assertThat(browser.getPageTitle(),is(equalTo("Zombie Dash : Conference Tasks")));
    }


}

