package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TaskRepository {

    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_TASK = "INSERT INTO zombie_task values(?,?,?,?)";
    private static final String RETRIEVE_TASK_FOR_CONFERENCE="SELECT * FROM zombie_task WHERE CONFERENCE_ID = ?";

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UUID insertTask(Task task) {
        jdbcTemplate.update(INSERT_TASK, task.getName(), task.getId(), task.getDescription(), task.getConferenceId());
        return task.getId();
    }
    public List<Task> fetchTasksForConference(UUID conferenceID) {
        List<Map<String,Object>> tasksForConference = jdbcTemplate.queryForList(RETRIEVE_TASK_FOR_CONFERENCE,conferenceID);
        ArrayList<Task> tasks = new ArrayList<Task>();
        for (Map<String, Object> resultRow : tasksForConference) {
            tasks.add(
                    new Task(
                            (String) resultRow.get("name"),
                            (UUID) resultRow.get("id"),
                            (String) resultRow.get("description"),
                            (UUID) resultRow.get("CONFERENCE_ID")));
        }
        return tasks;
    }
}
