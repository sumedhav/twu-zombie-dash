package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class OptionRepository {

    private final String SELECT_ALL_VALID_OPTIONS = "select * from zombie_option where question_Id=?";
    private final String INSERT_OPTION = "INSERT INTO zombie_option values(?,?,?,?)";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OptionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Option> fetchAllOptions(UUID questionId) {
        Object[] arg = new Object[]{questionId};
        return jdbcTemplate.query(SELECT_ALL_VALID_OPTIONS, arg, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                Boolean correct;
                String correctnessValue = resultSet.getString("correct");
                if (correctnessValue.equalsIgnoreCase("true") || correctnessValue.equalsIgnoreCase("t")) {
                    correct = true;
                } else {
                    correct = false;
                }
                return new Option((UUID) resultSet.getObject("Id"),
                        resultSet.getString("Text"), correct, (UUID)resultSet.getObject("question_id"));
            }
        });
    }

    public UUID insertOption(UUID questionId, Option option) {
        UUID optionId = UUID.randomUUID();
        jdbcTemplate.update(INSERT_OPTION, optionId, option.getText(), option.isCorrect(), questionId);
        return optionId;
    }
}