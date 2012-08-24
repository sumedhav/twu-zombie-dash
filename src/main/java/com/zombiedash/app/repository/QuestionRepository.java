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
    private static final String SELECT_ALL_QUESTIONS = "select * from zombie_question";
    private static final String SELECT_ALL_VALID_OPTIONS = "select zombie_option.question_id,zombie_option.text,zombie_option.correct  from zombie_question,zombie_option where zombie_question.Id = ? and zombie_question.Id=zombie_option.question_Id";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QuestionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        setUpQuestions();
    }

    private void setUpQuestions(){
        try{
        givenAQuestionWith(15, "Where is Red Fort");
        givenAnOptionFor(15, 1, "Delhi", true);
        givenAnOptionFor(15, 2, "Paris", false);
        givenAnOptionFor(15, 3, "New York", false);
        givenAQuestionWith(2, "Is it lunch time?");
        givenAnOptionFor(2, 1, "I bet it is", true);
        givenAnOptionFor(2, 2, "No thanks, fasting at the moment", false);
        }catch(Exception ignored){}

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
    private void givenAQuestionWith(int id, String text) {
        jdbcTemplate.execute(String.format(
                "insert into zombie_question (ID,Text) values(%d, '%s')", id, text));
    }

    private void givenAnOptionFor(int questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.execute(String.format("insert into zombie_option (id,question_id,text,correct) " +
                "values (%d, %d, '%s', %b)",
                optionId, questionId, text, correct));
    }
}
