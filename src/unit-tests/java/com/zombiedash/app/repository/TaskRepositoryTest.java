package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class TaskRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldInsertTaskIntoDatabase() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
        String name = "";
        String QUERY_BY_NAME = "INSERT INTO zombie_task values(?,?,?,?)";
        when(jdbcTemplate.update(anyString(),anyString(), any(UUID.class), anyString(), any(UUID.class))).thenReturn(1);
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        Task task = mock(Task.class);
        UUID id = taskRepository.insertTask(task);
        verify(jdbcTemplate).update(QUERY_BY_NAME, task.getName(), task.getId(),task.getDescription(),task.getConferenceId() );
    }


    @Test
    public void shouldRetrieveTasksForAConference() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
        String RETRIEVE_TASK_FOR_CONFERENCE="SELECT * FROM zombie_task WHERE CONFERENCE_ID = ?";
        final UUID conferenceId=UUID.randomUUID();
        when(jdbcTemplate.queryForList(RETRIEVE_TASK_FOR_CONFERENCE,(UUID.class))).thenAnswer(new Answer<List<Task>>() {
            @Override
            public List<Task> answer(InvocationOnMock invocation) throws Throwable {
                List<Task> resultBlock = new ArrayList<Task>();
                resultBlock.add(generateTaskContent(conferenceId));
                resultBlock.add(generateTaskContent(conferenceId));
                return resultBlock;
            }

            private Task generateTaskContent(UUID conferenceId) {
                Task task=new Task(anyString(),UUID.randomUUID(),"Very good task",conferenceId);
                return task;
            }
        });
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        List<Task> tasksForConference = taskRepository.fetchTasksForConference(conferenceId);
        for (Task task : tasksForConference) {
            assertThat(task.getName(), is(equalTo("My task")));
        }
    }
}
