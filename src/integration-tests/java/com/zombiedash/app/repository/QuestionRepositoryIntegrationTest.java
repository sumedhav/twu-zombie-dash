package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.test.matchers.QuestionMatcher;
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

import static com.zombiedash.app.test.matchers.QuestionMatcher.aQuestionWith;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class QuestionRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private OptionRepository optionRepository;

    private QuestionRepository questionRepository;
    private UUID taskId1;

    @Before
    public void setUp() {
        jdbcTemplate.execute("DELETE zombie_question");
        questionRepository = new QuestionRepository(jdbcTemplate);
    }

    private void insertDataIntoDatabase(){
        UUID questionId1 = UUID.randomUUID();
        taskId1 = UUID.randomUUID();
        UUID conferenceId = insertConference();
        insertTask("charles_task", taskId1, "sample description", conferenceId);
        insertQuestion(questionId1, "Where is Red Fort", taskId1);
        insertOption(questionId1, 10, "Delhi", true);
        insertOption(questionId1, 20, "Paris", false);
        insertOption(questionId1, 30, "New York", false);

        UUID questionId2 = UUID.randomUUID();
        UUID taskId2 = UUID.randomUUID();
        insertTask("sumedha", taskId2, "description when", conferenceId);
        insertQuestion(questionId2, "Is it lunch time?", taskId2);
        insertOption(questionId2, 10, "I bet it is", true);
        insertOption(questionId2, 20, "No thanks, fasting at the moment", false);

    }
    
    @Test
    public void shouldRetrieveAllQuestionsOfATask() {
        insertDataIntoDatabase();
        int noOfQuestions = 1;
        List<Question> questions = questionRepository.fetchAllQuestions(taskId1.toString());
        assertThat(questions.size(), is(noOfQuestions));
        Matcher<Iterable<Question>> matchTheInsertedQuestions = hasItems(
                QuestionMatcher.aQuestionWith("Where is Red Fort",
                        new HashMap<String, Boolean>() {{
                            put("Delhi", true);
                            put("Paris", false);
                            put("New York", false);
                        }}
                )
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
        optionList.add(new Option(UUID.randomUUID(), "Bangalore", true, questionId));
        optionList.add(new Option(UUID.randomUUID(), "Paris", false, questionId));
        optionList.add(new Option(UUID.randomUUID(), "Johannesburg", false, questionId));
        return optionList;
    }

    private UUID insertConference() {
        String conferenceId = "372b7e07-4cbd-47e3-90cd-7f166c2c29df";
        jdbcTemplate.update("INSERT INTO zombie_conference values(?,?,?,?,?,?,?,?)",UUID.fromString(conferenceId), "Spring", "Spring", "Spring", "Spring", "2012-12-12", "2012-12-30", 12);
        return UUID.fromString(conferenceId);
    }

    private void insertTask(String name, UUID taskID, String description, UUID conferenceId) {
        jdbcTemplate.update("INSERT INTO zombie_task values(?,?,?,?)", name, taskID, description, conferenceId);
    }

    private void insertQuestion(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    private void insertOption(UUID questionId, int optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, text, correct, questionId);
    }

}

