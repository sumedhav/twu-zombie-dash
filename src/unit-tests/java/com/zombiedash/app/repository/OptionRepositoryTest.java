package com.zombiedash.app.repository;

import com.zombiedash.app.model.Option;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

import static org.hamcrest.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OptionRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;
    private OptionRepository optionRepository;

    @Before
    public void setUp() throws Exception {
        jdbcTemplate = mock(JdbcTemplate.class);
        optionRepository = new OptionRepository(jdbcTemplate);
    }

    @Test
    public void shouldInsertOptionsIntoDatabase() {
        when(jdbcTemplate.update(anyString(), (UUID)anyObject(), anyString(), anyBoolean(), (UUID)anyObject())).thenReturn(1);

        Option option = mock(Option.class);

        when(option.getText()).thenReturn("ZombieDash Task Question");
        when(option.isCorrect()).thenReturn(true);

        UUID questionId = UUID.randomUUID();
        UUID optionId = optionRepository.insertOption(questionId, option);

        verify(jdbcTemplate).update("INSERT INTO zombie_option values(?,?,?,?)",
                optionId, "ZombieDash Task Question", true, questionId);
    }
}