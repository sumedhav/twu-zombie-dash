package com.zombiedash.app.repository;

import com.zombiedash.app.model.Question;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class QuestionRepository {
    private final String SELECT_ALL_QUESTIONS = "select * from zombie_question";
    private final String INSERT_QUESTION = "INSERT INTO zombie_question values(?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private OptionRepository optionRepository;

    @Autowired
    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.optionRepository = new OptionRepository(jdbcTemplate);
    }

    public List<Question> listAllQuestions() {
        return jdbcTemplate.query(SELECT_ALL_QUESTIONS, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new Question((UUID)resultSet.getObject("Id"),
                        resultSet.getString("Text"), optionRepository.listAllOptions((UUID) resultSet.getObject("Id")), (UUID)resultSet.getObject("task_id"));
            }
        });
    }

    public UUID insertQuestion(UUID taskId, Question question) {
        UUID primaryQuestionId = UUID.randomUUID();
        jdbcTemplate.update(INSERT_QUESTION, primaryQuestionId, question.getText(), taskId);
        return primaryQuestionId;
    }

}
