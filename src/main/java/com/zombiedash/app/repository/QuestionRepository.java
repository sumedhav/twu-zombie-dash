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

@Repository
public class QuestionRepository {
    private static final String SELECT_ALL_QUESTIONS = "select * from Question";
    private static final String SELECT_ALL_VALID_OPTIONS = "select option.question_id,option.text,option.correct  from Question,Option where question.Id = ? and question.Id=option.question_Id";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<Option> listAllOptions(int questionId){
        Object[] arg=new Object[]{questionId};

        return jdbcTemplate.query(SELECT_ALL_VALID_OPTIONS,arg, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                Boolean correct=resultSet.getString("correct").equalsIgnoreCase("true")?true:false;
                return new Option(Integer.parseInt(resultSet.getString("question_Id")),
                        resultSet.getString("Text"), correct);
            }
        });
    }
    public List<Question> listAllQuestions() {
        return jdbcTemplate.query(SELECT_ALL_QUESTIONS, new RowMapper() {
            @Override
            public Object mapRow(ResultSet resultSet, int i) throws SQLException {

                return new Question(Integer.parseInt(resultSet.getString("Id")),
                        resultSet.getString("Text"),listAllOptions(Integer.parseInt(resultSet.getString("Id"))));
            }
        });
    }
}
