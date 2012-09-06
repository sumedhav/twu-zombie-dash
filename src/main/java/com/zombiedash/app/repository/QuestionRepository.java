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
    private static final String SELECT_ALL_QUESTIONS = "select * from zombie_question";
    private static final String SELECT_ALL_VALID_OPTIONS = "select zombie_option.question_id,zombie_option.text,zombie_option.correct from zombie_question,zombie_option where zombie_question.Id = ? and zombie_question.Id=zombie_option.question_Id";
    private JdbcTemplate jdbcTemplate;
    private static final String INSERT_QUESTION = "INSERT INTO zombie_question values(?,?,?)";

    @Autowired
    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Option> listAllOptions(UUID questionId){
        Object[] arg=new Object[]{questionId};
        return jdbcTemplate.query(SELECT_ALL_VALID_OPTIONS,arg, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                Boolean correct;
                String correctnessValue=resultSet.getString("correct");
                if(correctnessValue.equalsIgnoreCase("true")||correctnessValue.equalsIgnoreCase("t")){
                    correct=true;
                }
                else {
                    correct=false;
                }
                return new Option((UUID)resultSet.getObject("question_Id"),
                        resultSet.getString("Text"), correct);
            }
        });
    }

    public List<Question> listAllQuestions() {
        return jdbcTemplate.query(SELECT_ALL_QUESTIONS, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new Question((UUID)resultSet.getObject("Id"),
                        resultSet.getString("Text"),listAllOptions((UUID)resultSet.getObject("Id")), (UUID)resultSet.getObject("task_id"));
            }
        });
    }

    public UUID insertQuestion(UUID taskId, Question question) {
        UUID primaryId = UUID.randomUUID();
        jdbcTemplate.update(INSERT_QUESTION, primaryId, question.getText(), taskId);
        return primaryId;
    }
}
