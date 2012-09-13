package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import com.zombiedash.app.model.Question;
import com.zombiedash.app.test.matchers.QuestionMatcher;
import com.zombiedash.app.web.page.tests.helper.TaskTestDataManager;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.assertThat;

@ContextConfiguration(locations = "/test-application-context.xml")
public class QuestionRepositoryIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private OptionRepository optionRepository;

    private QuestionRepository questionRepository;
    private UUID taskId1;
    private TaskTestDataManager taskTestDataManager;

    @Before
    public void setUp() {
        questionRepository = new QuestionRepository(jdbcTemplate);
        taskTestDataManager =new TaskTestDataManager(jdbcTemplate);
        taskTestDataManager.clearTaskRelatedTables();
    }

    private void insertDataIntoDatabase(){
        UUID questionId1 = UUID.randomUUID();
        taskId1 = UUID.randomUUID();
        UUID conferenceId = taskTestDataManager.insertConference();
        taskTestDataManager.insertTask("charles_task", taskId1, "sample description", conferenceId);
        taskTestDataManager.insertQuestion(questionId1, "Where is Red Fort", taskId1);
        taskTestDataManager.insertOption(questionId1, 10, "Delhi", true);
        taskTestDataManager.insertOption(questionId1, 20, "Paris", false);
        taskTestDataManager.insertOption(questionId1, 30, "New York", false);

        UUID questionId2 = UUID.randomUUID();
        UUID taskId2 = UUID.randomUUID();
        taskTestDataManager.insertTask("sumedha", taskId2, "sample description", conferenceId);
        taskTestDataManager.insertQuestion(questionId2, "Is it lunch time?", taskId2);

        taskTestDataManager.insertOption(questionId2, 70, "I bet it is", true);
        taskTestDataManager.insertOption(questionId2, 80, "No thanks, fasting at the moment", false);

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
    public void shouldReturnCorrectOption() {
        UUID questionId = UUID.randomUUID();
        UUID taskId = UUID.randomUUID();
        UUID correctOptionId = UUID.randomUUID();

        Question aQuestion = new Question(questionId, "Where are you?", createOptionList(questionId, correctOptionId), taskId);

        assertThat(aQuestion.getValidOption(), equalTo(correctOptionId));
    }

    private List<Option> createOptionList(UUID questionId, UUID correctOptionId) {
        List<Option> optionList = new ArrayList<Option>();
        optionList.add(new Option(correctOptionId, "Bangalore", true, questionId));
        optionList.add(new Option(UUID.randomUUID(), "Paris", false, questionId));
        optionList.add(new Option(UUID.randomUUID(), "Johannesburg", false, questionId));
        return optionList;
    }

}

