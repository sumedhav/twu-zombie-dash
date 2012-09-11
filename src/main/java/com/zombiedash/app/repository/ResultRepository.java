package com.zombiedash.app.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class ResultRepository {

    private JdbcTemplate jdbcTemplate;
    private final String SELECT_INCOMPLETE_TASKS_FOR_ATTENDEE ="SELECT ID FROM zombie_task AS task," +
            " zombie_attendee_info AS attendee WHERE attendee.username = ? AND attendee.conference_ID = task.CONFERENCE_ID" +
            " AND ID NOT IN (SELECT task_ID FROM zombie_attendee_score WHERE username = ?)";
    private final String ADD_COMPLETED_TASK="INSERT INTO zombie_attendee_score VALUES(?,?,?)";
    @Autowired
    public ResultRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<String> getIncompleteTasks(String username) {
        Object[] args=new Object[]{username, username};
        List<String> allTasks = jdbcTemplate.query(SELECT_INCOMPLETE_TASKS_FOR_ATTENDEE, args, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("ID");
            }
        });
        return  allTasks;
    }

    public int addCompletedTask(String username, UUID taskID, int score){
        return jdbcTemplate.update(ADD_COMPLETED_TASK,username,taskID,score);
    }

}
