package com.zombiedash.app.web.page.tests.helper;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class TriviaGameTestDataCreationTemplate {
    private JdbcTemplate jdbcTemplate;

    public TriviaGameTestDataCreationTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void clearTables(){
        jdbcTemplate.execute("DELETE zombie_option");
        jdbcTemplate.execute("DELETE zombie_question");
        jdbcTemplate.execute("DELETE zombie_task ");
    }

    public void givenATaskWith(String name, UUID taskID) {
        jdbcTemplate.update("INSERT INTO zombie_task values(?,?)", name, taskID);
    }

    public void givenAQuestionWith(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    public void givenAnOptionFor(UUID questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, questionId, text, correct);
    }

}
