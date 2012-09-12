package com.zombiedash.app.helper;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class TaskTestDataManager {
    private JdbcTemplate jdbcTemplate;

    public TaskTestDataManager(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void clearTaskRelatedTables(){
        jdbcTemplate.execute("DELETE zombie_conference");
        jdbcTemplate.execute("DELETE zombie_question");
        jdbcTemplate.execute("DELETE zombie_option");
        jdbcTemplate.execute("DELETE zombie_task");
        jdbcTemplate.execute("DELETE zombie_attendee_score");
    }

    public void insertTask(String name, UUID taskID, String s, UUID conferenceId) {
        jdbcTemplate.update("INSERT INTO zombie_task values(?,?,?,?)", name, taskID, s, conferenceId);
    }

    public void insertQuestion(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    public void insertOption(UUID questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, text, correct,  questionId);
    }

    public UUID insertConference() {
        String conferenceId = "372b7e07-4cbd-47e3-90cd-7f166c2c29df";
        jdbcTemplate.update("INSERT INTO zombie_conference values(?,?,?,?,?,?,?,?)",UUID.fromString(conferenceId), "Spring", "Spring", "Spring", "Spring", "2012-12-12", "2012-12-30", 12);
        return UUID.fromString(conferenceId);
    }
}
