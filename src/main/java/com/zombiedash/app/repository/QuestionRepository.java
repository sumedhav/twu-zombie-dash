package com.zombiedash.app.repository;

import com.zombiedash.app.model.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class QuestionRepository {
    private static final String SELECT_ALL_QUESTIONS = "select * from Question";
    private JdbcTemplate jdbcTemplate;

    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Question> listAllQuestions() {
        return jdbcTemplate.query(SELECT_ALL_QUESTIONS, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Question(Integer.parseInt(resultSet.getString("Id")), resultSet.getString("Text"));
            }
        });
    }
}
