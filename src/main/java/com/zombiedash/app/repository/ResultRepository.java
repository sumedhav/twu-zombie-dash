package com.zombiedash.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResultRepository {

    private JdbcTemplate jdbcTemplate;
    private final String SELECT_COMPLETED_TASK_FOR_ATTENDEE ="SELECT task_ID FROM zombie_attendee_score WHERE username = ?";
    private final String SELECT_ALL_TASKS_OF_ATTENDEE = "SELECT ID FROM zombie_task AS task," +
            " zombie_attendee_info AS attendee WHERE attendee.username = ? AND attendee.conference_ID = task.CONFERENCE_ID";

    @Autowired
    public ResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getCompletedTasks(String username) {
        Object[] args=new Object[]{username};
        List<String> completedTasks = jdbcTemplate.query(SELECT_COMPLETED_TASK_FOR_ATTENDEE, args, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("task_ID");
            }
        });
        return  completedTasks;
    }

    public List<String> getAllTasks(String username) {
        Object[] args=new Object[]{username};
        List<String> allTasks = jdbcTemplate.query(SELECT_ALL_TASKS_OF_ATTENDEE, args, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("ID");
            }
        });
        return  allTasks;
    }


}
