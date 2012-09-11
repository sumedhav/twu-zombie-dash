package com.zombiedash.app.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class ResultRepositoryIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ResultRepository resultRepository;

    private final String SELECT_COMPLETED_TASK_FOR_USER="SELECT task_ID FROM zombie_attendee_score WHERE username = ?;";
    private final String CREATE_CONFERENCE="INSERT INTO zombie_conference VALUES(?,?,?,?,?,?,?,?,?);";
    @Test
    public void shouldReturnCompletedTasks() throws Exception {
       // jdbcTemplate.execute(String.format("INSERT INTO zombie_conference VALUES(cast('%s' AS uuid),'name','topic','description'," +
         //       "'venue','start','end',5);", conferenceId.toString()));

    }
}
