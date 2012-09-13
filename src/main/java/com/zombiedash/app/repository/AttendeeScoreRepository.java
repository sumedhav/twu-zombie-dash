package com.zombiedash.app.repository;

import com.zombiedash.app.model.AttendeeAnswer;
import com.zombiedash.app.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class AttendeeScoreRepository {

    private JdbcTemplate jdbcTemplate;
    private final String SELECT_INCOMPLETE_TASKS_FOR_ATTENDEE ="SELECT * FROM zombie_task AS task," +
            " zombie_attendee_info AS attendee WHERE attendee.username = ? AND attendee.conference_ID = task.CONFERENCE_ID" +
            " AND ID NOT IN (SELECT task_ID FROM zombie_attendee_score WHERE username = ?)";
    private final String ADD_COMPLETED_TASK = "INSERT INTO zombie_attendee_score VALUES(?,?,?)";
    private final String SELECT_SCORES_OF_ATTENDEE = "SELECT sum(score) FROM zombie_attendee_score WHERE username = ?";
    private final String COUNT_OF_TASK_OF_ATTENDEE =  "SELECT count(*) FROM zombie_attendee_score WHERE username = ? AND " +
            "task_ID = ?";
    private final String INSERT_USER_ANSWER = "INSERT INTO zombie_attendee_answers VALUES(?,?,?,?)";
    private final String SELECT_ANSWER_OF_ATTENDEE = "SELECT * FROM zombie_attendee_answers WHERE username = ? and task_ID = ?";

    @Autowired
    public AttendeeScoreRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Task> fetchIncompleteTasks(String username) {
        Object[] args=new Object[]{username, username};
        List<Task> incompleteTasks = jdbcTemplate.query(SELECT_INCOMPLETE_TASKS_FOR_ATTENDEE, args, new RowMapper<Task>() {
            @Override
            public Task mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Task(resultSet.getString("name"),
                                (UUID)resultSet.getObject("ID"),
                                resultSet.getString("Description"),
                                (UUID) resultSet.getObject("CONFERENCE_ID")
                                );
            }
        });
        return  incompleteTasks;
    }

    public int addCompletedTask(String username, UUID taskID, int score){
        return jdbcTemplate.update(ADD_COMPLETED_TASK,username,taskID,score);
    }

    public Integer fetchAttendeeScore(String username) {
        Object[] args = new Object[]{username};
        Integer score = jdbcTemplate.queryForInt(SELECT_SCORES_OF_ATTENDEE,args);
        return score;
    }

    public boolean doesTaskExist(String username, String taskId) {
        Object[] args = new Object[]{username,UUID.fromString(taskId)};
        Integer doesTaskExist = jdbcTemplate.queryForInt(COUNT_OF_TASK_OF_ATTENDEE,args);
        if(doesTaskExist == 0) return false;
        return true;
    }

    public int insertAnswers(String username, UUID taskId, Map<String, String> userAnswer) {
        try {
            for (String key : userAnswer.keySet()) {

                if (!key.startsWith("submit")) {
                    jdbcTemplate.update(INSERT_USER_ANSWER, username, taskId, UUID.fromString(key), UUID.fromString(userAnswer.get(key)));
                }
            }

            return 1;
        } catch (Exception ex) {
            return 0;
        }


    }

    public List<AttendeeAnswer> fetchAnswers(String username, UUID taskId) {
        Object[] args = new Object[]{username, taskId};
        return jdbcTemplate.query(SELECT_ANSWER_OF_ATTENDEE, args, new RowMapper<AttendeeAnswer>() {
            @Override
            public AttendeeAnswer mapRow(ResultSet resultSet, int i) throws SQLException {
                return new AttendeeAnswer(resultSet.getString("username"),
                        (UUID) resultSet.getObject("task_ID"),
                        (UUID) resultSet.getObject("question_ID"),
                        (UUID) resultSet.getObject("option_ID"));  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
