package com.zombiedash.app.repository;

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
        String name = "zombie_task_one";
        String QUERY_BY_NAME = "INSERT INTO zombie_task values(?,?)";
        when(jdbcTemplate.update(anyString(), any(UUID.class), anyString())).thenReturn(1);
        TaskRepository taskRepository = new TaskRepository(jdbcTemplate);
        UUID id = taskRepository.insertTask(name);
        verify(jdbcTemplate).update(QUERY_BY_NAME, name ,id);
    }
}
