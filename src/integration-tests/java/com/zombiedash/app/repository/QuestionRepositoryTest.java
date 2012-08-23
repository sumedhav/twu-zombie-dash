package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.zombiedash.app.repository.QuestionMatcher.aQuestionWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class QuestionRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<Option> anOptionList;

    private void setUpQuestions(){
        givenAQuestionWith(1, "Where is Red Fort");
        givenAnOptionFor(1, 1, "Delhi", true);
        givenAnOptionFor(1, 2, "Paris", false);
        givenAnOptionFor(1, 3, "New York", false);
        givenAQuestionWith(2, "Is it lunch time?");
        givenAnOptionFor(2, 1, "I bet it is", true);
        givenAnOptionFor(2, 2, "No thanks, fasting at the moment", false);

    }
    @Test
    public void shouldRetrieveAllQuestions() {
        setUpQuestions();
        QuestionRepository questionRepository = new QuestionRepository(jdbcTemplate);
        List<Question> questions = questionRepository.listAllQuestions();
        assertThat(questions.size(), is(2));
        Matcher<Iterable<Question>> matchTheInsertedQuestions = hasItems(
                QuestionMatcher.aQuestionWith("Where is Red Fort",
                        new HashMap<String, Boolean>() {{
                            put("Delhi", true);
                            put("Paris", false);
                            put("New York", false);
                        }}
                ),
                aQuestionWith("Is it lunch time?", new HashMap<String, Boolean>() {{
                    put("I bet it is", true);
                    put("No thanks, fasting at the moment", false);
                }})
        );
        assertThat(questions, matchTheInsertedQuestions);
    }

    @Test
    public void shouldReturnCorrectOption(){
        anOptionList = new ArrayList<Option>();
        anOptionList.add(new Option(1, "Bangalore", true));
        anOptionList.add(new Option(2, "Paris", false));
        anOptionList.add(new Option(3, "Johannesburg", false));

        String expectedCorrectOption = "Bangalore";
        Question aQuestion = new Question(1, "Where are you?", anOptionList);

        assertThat(aQuestion.getValidOption(), equalTo(expectedCorrectOption));
    }
    private void givenAQuestionWith(int id, String text) {
        jdbcTemplate.execute(String.format(
                "insert into Question (ID,Text) values(%d, '%s')", id, text));
    }

    private void givenAnOptionFor(int questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.execute(String.format("insert into Option (id,question_id,text,correct) " +
                "values (%d, %d, '%s', %b)",
                optionId, questionId, text, correct));
    }

}

