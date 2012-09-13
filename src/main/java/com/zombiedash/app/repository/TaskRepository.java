package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class TaskRepository {

    private JdbcTemplate jdbcTemplate;
    private final String INSERT_TASK = "INSERT INTO zombie_task values(?,?,?,?)";
    private final String RETRIEVE_TASK_FOR_CONFERENCE="SELECT * FROM zombie_task WHERE CONFERENCE_ID = ?";
    private final String FETCH_TASK = "SELECT * FROM zombie_task WHERE ID = ?";

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


    public Task fetchTask(UUID uuid) {
        Object[] args=new Object[]{uuid};
        return jdbcTemplate.queryForObject(FETCH_TASK,args,new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Task(resultSet.getString("name"),
                      (UUID) resultSet.getObject("ID"),
                       resultSet.getString("Description"),
                       (UUID)resultSet.getObject("CONFERENCE_ID")) ;
            }
        });
    }
}
