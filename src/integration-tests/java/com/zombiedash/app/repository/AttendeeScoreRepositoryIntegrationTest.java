package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class AttendeeScoreRepositoryIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AttendeeScoreRepository resultRepository;

    private final String CREATE_CONFERENCE="INSERT INTO zombie_conference VALUES(?,?,?,?,?,?,?,?)";
    private final String CREATE_USER="INSERT INTO zombie_users VALUES(?,?,?,?,?)";
    private final String CREATE_ATTENDEE = "INSERT INTO zombie_attendee_info VALUES(?,?,?,?,?,?,?)";
    private final String CREATE_TASK = "INSERT INTO zombie_task values(?,?,?,?)";
    private String username;
    private UUID firstTaskId;
    private UUID secondTaskId;

    @Before
    public void setupDb(){
        username="username";
        resultRepository = new AttendeeScoreRepository(jdbcTemplate);
        UUID conferenceId = UUID.randomUUID();
        firstTaskId = UUID.randomUUID();
        secondTaskId = UUID.randomUUID();
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
    public void shouldReturnAllTasksAsIncompleteTasksWhenNoTaskIsCompleted(){
        List<Task> incompleteTasks=resultRepository.getIncompleteTasks(username);
        assertEquals(incompleteTasks.size(),2);
    }

    @Test
    public void shouldAddCompletedTask(){
        int result = resultRepository.addCompletedTask(username,firstTaskId,40);
        assertEquals(result,1);
    }

    @Test
    public void shouldReturnIncompleteTasksWhenSomeTasksAreCompleted(){
        resultRepository.addCompletedTask(username,firstTaskId,40);
        List<Task> incompleteTasks=resultRepository.getIncompleteTasks(username);
        assertEquals(incompleteTasks.size(),1);
        assertEquals(incompleteTasks.get(0).getId(),secondTaskId);
    }


    @Test
    public void shouldGetScoreOfAllTasksCompletedByAttendee() throws Exception {
        jdbcTemplate.update("INSERT INTO zombie_attendee_score values(?,?,?)", username, firstTaskId, 1);
        jdbcTemplate.update("INSERT INTO zombie_attendee_score values(?,?,?)", username, secondTaskId, 3);
        assertThat(resultRepository.getAttendeeScore(username), is(4));
    }
}
