package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import com.zombiedash.app.web.page.tests.helper.TaskTestDataManager;
import com.zombiedash.app.web.page.tests.helper.UserTestDataManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class AttendeeScoreRepositoryIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private AttendeeScoreRepository attendeeScoreRepository;

    private final String CREATE_CONFERENCE="INSERT INTO zombie_conference VALUES(?,?,?,?,?,?,?,?)";
    private final String CREATE_USER="INSERT INTO zombie_users VALUES(?,?,?,?,?)";
    private final String CREATE_ATTENDEE = "INSERT INTO zombie_attendee_info VALUES(?,?,?,?,?,?,?,?,?)";
    private final String CREATE_TASK = "INSERT INTO zombie_task values(?,?,?,?)";
    private String username;
    private UUID firstTaskId;
    private UUID secondTaskId;
    private Map<String, String> userAnswer;
    private TaskTestDataManager taskTestDataManager;
    private UserTestDataManager userTestDataManager;

    @Before
    public void setupDb(){
        username="username";
        userAnswer = new HashMap<String, String>();
        attendeeScoreRepository = new AttendeeScoreRepository(jdbcTemplate);
        UUID conferenceId = UUID.randomUUID();
        firstTaskId = UUID.randomUUID();
        secondTaskId = UUID.randomUUID();
        taskTestDataManager = new TaskTestDataManager(jdbcTemplate);
        taskTestDataManager.clearTaskRelatedTables();
        userTestDataManager = new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
        jdbcTemplate.update(CREATE_CONFERENCE,
                conferenceId,
                "name",
                "topic",
                "description",
                "venue",
                "start",
                "end",
                5);
        createTask("Task A", firstTaskId, conferenceId);
        createTask("Task B", secondTaskId, conferenceId);
        createAttendee(conferenceId);
    }


    private void createTask(String taskName, UUID taskId, UUID conferenceId) {
        jdbcTemplate.update(CREATE_TASK,
                taskName,
                taskId,
                "Task Description",
                conferenceId);
    }

    private void createAttendee(UUID conferenceId) {
        jdbcTemplate.update(CREATE_USER,
                username,
                "password1",
                2,
                "Attendee",
                "attendee@zombie.com");
        jdbcTemplate.update(CREATE_ATTENDEE,
                username,
                "dob",
                "country",
                null,
                null,
                null,
                true,
                true,
                conferenceId);
    }

    private void createQuestion(UUID id, String text, UUID taskId) {
        jdbcTemplate.update("insert into zombie_Question values(?,?,?)", id, text, taskId);
    }

    private void createOption(UUID questionId, UUID optionId, String text, boolean correct) {
        jdbcTemplate.update("INSERT INTO zombie_option values(?,?,?,?)", optionId, text, correct, questionId);
    }

    @Test
    public void shouldReturnAllTasksAsIncompleteTasksWhenNoTaskIsCompleted(){
        List<Task> incompleteTasks= attendeeScoreRepository.fetchIncompleteTasks(username);
        assertEquals(incompleteTasks.size(),2);
    }

    @Test
    public void shouldAddCompletedTask(){
        int result = attendeeScoreRepository.addCompletedTask(username,firstTaskId,40);
        assertEquals(result,1);
    }

    @Test
    public void shouldReturnIncompleteTasksWhenSomeTasksAreCompleted(){
        attendeeScoreRepository.addCompletedTask(username, firstTaskId, 40);
        List<Task> incompleteTasks= attendeeScoreRepository.fetchIncompleteTasks(username);
        assertEquals(incompleteTasks.size(),1);
        assertEquals(incompleteTasks.get(0).getId(),secondTaskId);
    }


    @Test
    public void shouldGetScoreOfAllTasksCompletedByAttendee() throws Exception {
        jdbcTemplate.update("INSERT INTO zombie_attendee_score values(?,?,?)", username, firstTaskId, 1);
        jdbcTemplate.update("INSERT INTO zombie_attendee_score values(?,?,?)", username, secondTaskId, 3);
        assertThat(attendeeScoreRepository.fetchAttendeeScore(username), is(4));
    }

    @Test
    public void shouldReturnTrueIfTaskExistsInDatabase() throws Exception {
        jdbcTemplate.update("INSERT INTO zombie_attendee_score values(?,?,?)", username, firstTaskId, 1);
        assertThat(attendeeScoreRepository.doesTaskExist(username,firstTaskId.toString()), is(true));
    }

    @Test
    public void shouldReturnFalseIfTaskDoesNotExistInDatabase() throws Exception {
        assertThat(attendeeScoreRepository.doesTaskExist(username, firstTaskId.toString()), is(false));
    }


    public void createQuestionAndAnswerForTask(UUID taskId, UUID questionId,UUID optionId){
        createQuestion(questionId, "What's your name?", taskId);
        createOption(questionId, optionId, "Charles", true);
        userAnswer.put(questionId.toString(), optionId.toString());

    }

    @Test
    public void shouldInsertUserAnswersForOneTask() {
        UUID questionId = UUID.randomUUID();
        UUID firstOptionId = UUID.randomUUID();
        UUID secondOptionId=UUID.randomUUID();
        createQuestionAndAnswerForTask(firstTaskId, questionId, firstOptionId);
        UUID secondQuestionId = UUID.randomUUID();
        createQuestionAndAnswerForTask(firstTaskId, secondQuestionId, secondOptionId);

        int result = attendeeScoreRepository.insertAnswers(username, firstTaskId, userAnswer);
        assertThat(result, is(1));
    }

    @Test
    public void shouldInsertUserAnswersForDifferentTask() {
        Map<String, String> secondTaskUserAnswer = new HashMap();
        UUID questionId = UUID.randomUUID();
        UUID secondQuestionId = UUID.randomUUID();
        UUID optionId = UUID.randomUUID();
        UUID secondOptionId=UUID.randomUUID();
        createQuestionAndAnswerForTask(firstTaskId, questionId, optionId);
        createQuestionAndAnswerForTask(secondTaskId,secondQuestionId,secondOptionId);

        int result = attendeeScoreRepository.insertAnswers(username, firstTaskId, userAnswer);
        assertThat(result, is(1));

        secondTaskUserAnswer.put(questionId.toString(), optionId.toString());
        result = attendeeScoreRepository.insertAnswers(username,secondTaskId, userAnswer);

        assertThat(result, is(1));
    }

}
