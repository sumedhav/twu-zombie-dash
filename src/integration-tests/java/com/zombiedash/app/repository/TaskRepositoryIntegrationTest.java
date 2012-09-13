package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.model.Task;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class TaskRepositoryIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp()  {
        jdbcTemplate.execute("DELETE zombie_task");
    }


    @Test
    @Rollback(true)
    public void shouldInsertTaskIntoDatabase() {
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        String conferenceId = "372b7e07-4cbd-47e3-90cd-7f166c2c29df";
        Conference conference = new Conference(UUID.fromString(conferenceId), "Spring", "Spring", "Spring", "Spring", "2012-12-12", "2012-12-30", 12);
        conferenceRepository.insertConference(conference);
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        Task task = new Task("Hello", UUID.randomUUID(), "Description", UUID.fromString(conferenceId));
        UUID returnId = taskRepository.insertTask(task);
        UUID  databaseId= jdbcTemplate.queryForObject("SELECT id FROM zombie_task where name='Hello'",
                UUID.class);
        assertThat(databaseId, is(returnId));
    }

    @Test
    @Rollback(true)
    public void shouldRetrieveTaskForAConference() throws Exception {
        ConferenceRepository conferenceRepository = new ConferenceRepository(jdbcTemplate);
        String conferenceId = UUID.randomUUID().toString();
        Conference conference = new Conference(UUID.fromString(conferenceId), "Spring", "Spring", "Spring", "Spring", "2012-12-12", "2012-12-30", 12);
        conferenceRepository.insertConference(conference);
        String conferenceId2 = UUID.randomUUID().toString();
        Conference conference2 = new Conference(UUID.fromString(conferenceId2), "Rahul", "Spring", "Spring", "Spring", "2012-12-12", "2012-12-30", 12);
        conferenceRepository.insertConference(conference2);
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        Task task = new Task("Hello", UUID.randomUUID(), "Description", UUID.fromString(conferenceId));
        Task task2 = new Task("Hi", UUID.randomUUID(), "Description new", UUID.fromString(conferenceId));
        Task task3 = new Task("Hi", UUID.randomUUID(), "Description new", UUID.fromString(conferenceId2));
        taskRepository.insertTask(task);
        taskRepository.insertTask(task2);
        taskRepository.insertTask(task3);
        List<Task> tasksForConference = taskRepository.fetchTasksForConference(UUID.fromString(conferenceId));
        for (Task tasks : tasksForConference) {
            assertThat(tasks.getConferenceId().toString(),is(conferenceId));
        }
        assertThat(tasksForConference.size(),is(2));
    }
}
