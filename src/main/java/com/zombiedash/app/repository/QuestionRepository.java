package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
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
    private final String SELECT_ALL_QUESTIONS = "SELECT * from zombie_question WHERE TASK_ID = ?";
    private final String INSERT_QUESTION = "INSERT INTO zombie_question values(?,?,?)";

    private JdbcTemplate jdbcTemplate;

    private OptionRepository optionRepository;

    @Autowired
    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.optionRepository = new OptionRepository(jdbcTemplate);
    }

    public List<Question> fetchAllQuestions(String taskId) {
        Object[] args=new Object[]{UUID.fromString(taskId)};
        return jdbcTemplate.query(SELECT_ALL_QUESTIONS,args, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new Question((UUID)resultSet.getObject("Id"),
                        resultSet.getString("Text"), optionRepository.fetchAllOptions((UUID) resultSet.getObject("Id")), (UUID)resultSet.getObject("task_id"));
            }
        });
    }

    public UUID insertQuestion(Question question) {
        jdbcTemplate.update(INSERT_QUESTION, question.getQuestionId(), question.getText(), question.getTaskId());
        for(Option option: question.getOptions()) {
            optionRepository.insertOption(option);
        }
        return question.getQuestionId();
    }

}
