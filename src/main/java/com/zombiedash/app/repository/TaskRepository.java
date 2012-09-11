package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static org.mockito.Matchers.anyString;

@Repository
public class TaskRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_TASK = "INSERT INTO zombie_task values(?,?,?,?)";

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID insertTask(Task task) {
        jdbcTemplate.update(INSERT_TASK, task.getName(), task.getId(), task.getDescription(), task.getConferenceId());
        return task.getId();
    }
}
