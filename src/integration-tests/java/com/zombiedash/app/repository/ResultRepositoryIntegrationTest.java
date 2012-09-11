package com.zombiedash.app.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class ResultRepositoryIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ResultRepository resultRepository;

    private final String CREATE_CONFERENCE="INSERT INTO zombie_conference VALUES(?,?,?,?,?,?,?,?)";
    private final String CREATE_USER="INSERT INTO zombie_users VALUES(?,?,?,?,?)";
    private final String CREATE_ATTENDEE = "INSERT INTO zombie_attendee_info VALUES(?,?,?,?,?,?,?)";
    private final String CREATE_TASK = "INSERT INTO zombie_task values(?,?,?,?)";
    private String username;

    @Before
    public void setupDb(){
        username="username";
        resultRepository = new ResultRepository(jdbcTemplate);
        UUID conferenceId = UUID.randomUUID();
        UUID firstTaskId = UUID.randomUUID();
        UUID secondTaskId = UUID.randomUUID();
        clearAllTables();
        jdbcTemplate.update(CREATE_CONFERENCE,
                conferenceId,
                "name",
                "topic",
                "description",
                "venue",
                "start",
                "end",
                5);
        createTask("Task A", firstTaskId, conferenceId);
        createTask("Task B", secondTaskId, conferenceId);
        createAttendee(conferenceId);
    }

    private void clearAllTables() {
        jdbcTemplate.execute("DELETE zombie_conference");
        jdbcTemplate.execute("DELETE zombie_users");
    }

    private void createTask(String taskName, UUID taskId, UUID conferenceId) {
        jdbcTemplate.update(CREATE_TASK,
                taskName,
                taskId,
                "Task Description",
                conferenceId);
    }

    private void createAttendee(UUID conferenceId) {
        jdbcTemplate.update(CREATE_USER,
                username,
                "password1",
                2,
                "Attendee",
                "attendee@zombie.com");
        jdbcTemplate.update(CREATE_ATTENDEE,
                username,
                "dob",
                "country",
                null,
                null,
                null,
                conferenceId);
    }

    @Test
    public void shouldReturnEmptyWhenNoTaskIsCompleted(){
        List<String> completedTasks = resultRepository.getCompletedTasks(username);
        assertEquals(completedTasks.size(),0);
    }

    @Test
    public void shouldReturnAllTasksForAttendee(){
        List<String> allTasks=resultRepository.getAllTasks(username);
        assertEquals(allTasks.size(),2);
    }


}