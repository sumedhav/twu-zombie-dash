package com.zombiedash.app.repository;

import com.zombiedash.app.model.Task;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

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
}
