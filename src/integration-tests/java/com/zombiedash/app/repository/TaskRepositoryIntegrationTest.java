package com.zombiedash.app.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
    public void shouldInsertTaskIntoDatabase() {
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        UUID returnId = taskRepository.insert("zombie_task_one");
        UUID  databaseId= jdbcTemplate.queryForObject("SELECT id FROM zombie_task where name='zombie_task_one'",
                UUID.class);
        assertThat(databaseId, is(returnId));
    }
}
