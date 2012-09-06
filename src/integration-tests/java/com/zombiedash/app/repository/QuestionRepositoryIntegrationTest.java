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
    private QuestionRepository questionRepository;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DELETE zombie_question");
        questionRepository = new QuestionRepository(jdbcTemplate);
    }

    private void insertDataIntoDatabase(){
        UUID questionId1 = UUID.randomUUID();
        UUID taskId1 = UUID.randomUUID();
        insertTask("charles_task", taskId1);
        insertQuestion(questionId1, "Where is Red Fort", taskId1);
        insertOption(questionId1, 10, "Delhi", true);
        insertOption(questionId1, 20, "Paris", false);
        insertOption(questionId1, 30, "New York", false);

        UUID questionId2 = UUID.randomUUID();
        UUID taskId2 = UUID.randomUUID();
        insertTask("sumedha", taskId2);
        insertQuestion(questionId2, "Is it lunch time?", taskId2);
        insertOption(questionId2, 10, "I bet it is", true);
        insertOption(questionId2, 20, "No thanks, fasting at the moment", false);

    }
    
    @Test
    public void shouldRetrieveAllQuestions() {
        insertDataIntoDatabase();
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
        UUID taskId = UUID.randomUUID();
        createOptionList(questionId);

        Question aQuestion = new Question(questionId, "Where are you?", createOptionList(questionId), taskId);

        assertThat(aQuestion.getValidOption(), equalTo("Bangalore"));
    }

    private List<Option> createOptionList(UUID questionId) {
        List <Option> optionList = new ArrayList<Option>();
        optionList.add(new Option(questionId, "Bangalore", true));
        optionList.add(new Option(questionId, "Paris", false));
        optionList.add(new Option(questionId, "Johannesburg", false));
        return optionList;
    }

    private void insertTask(String name, UUID taskID) {
        jdbcTemplate.update("INSERT INTO zombie_task values(?,?)", name, taskID);
    }

    private void insertQuestion(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    private void insertOption(UUID questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, questionId, text, correct);
    }

}

