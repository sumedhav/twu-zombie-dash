package com.zombiedash.app.web.page.tests.helper;

import org.springframework.jdbc.core.JdbcTemplate;

public class TriviaGameTestDataCreationTemplate {
    private JdbcTemplate jdbcTemplate;

    public TriviaGameTestDataCreationTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void givenAQuestionWith(int id, String text) {
        jdbcTemplate.execute(String.format(
                "insert into Question (ID,Text) values(%d, '%s')", id, text));
    }

    public void givenAnOptionFor(int questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.execute(String.format("insert into Option (id,question_id,text,correct) " +
                "values (%d, %d, '%s', %b)",
                optionId, questionId, text, correct));
    }
}
