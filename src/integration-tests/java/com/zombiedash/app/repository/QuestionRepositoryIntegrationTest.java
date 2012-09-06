package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.repository.helper.QuestionMatcher;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static com.zombiedash.app.repository.helper.QuestionMatcher.aQuestionWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class QuestionRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private List<Option> anOptionList;
    private QuestionRepository questionRepository;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DELETE zombie_question");
        questionRepository = new QuestionRepository(jdbcTemplate);
    }

    private void setUpQuestions(){
        UUID questionID = UUID.randomUUID();
        UUID taskID = UUID.randomUUID();
        givenATaskWith("charles_task", taskID);
        givenAQuestionWith(questionID, "Where is Red Fort", taskID);
        givenAnOptionFor(questionID, 10, "Delhi", true);
        givenAnOptionFor(questionID, 20, "Paris", false);
        givenAnOptionFor(questionID, 30, "New York", false);
        UUID questionID1 = UUID.randomUUID();
        UUID taskID1 = UUID.randomUUID();
        givenATaskWith("sumedha", taskID1);
        givenAQuestionWith(questionID1, "Is it lunch time?", taskID1);
        givenAnOptionFor(questionID1, 10, "I bet it is", true);
        givenAnOptionFor(questionID1, 20, "No thanks, fasting at the moment", false);

    }
    
    @Test
    public void shouldRetrieveAllQuestions() {
        setUpQuestions();
        int noOfQuestions = 2;
        List<Question> questions = questionRepository.listAllQuestions();
        assertThat(questions.size(), is(noOfQuestions));
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
        UUID questionId = UUID.randomUUID();
        setUpQuestions();
        anOptionList = new ArrayList<Option>();
        anOptionList.add(new Option(questionId, "Bangalore", true));
        anOptionList.add(new Option(questionId, "Paris", false));
        anOptionList.add(new Option(questionId, "Johannesburg", false));

        String expectedCorrectOption = "Bangalore";
        Question aQuestion = new Question(questionId, "Where are you?", anOptionList);

        assertThat(aQuestion.getValidOption(), equalTo(expectedCorrectOption));
    }

    private void givenATaskWith(String name, UUID taskID) {
        jdbcTemplate.update("INSERT INTO zombie_task values(?,?)", name, taskID);
    }

    private void givenAQuestionWith(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    private void givenAnOptionFor(UUID questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, questionId, text, correct);
    }

}

