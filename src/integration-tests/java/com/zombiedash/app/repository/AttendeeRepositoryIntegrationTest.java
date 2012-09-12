package com.zombiedash.app.repository;

import com.zombiedash.app.helper.UserTestDataManager;
import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class AttendeeRepositoryIntegrationTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private AttendeeRepository attendeeRepository;
    private UserTestDataManager userTestDataManager;


    @Before
    public void setUp(){
        attendeeRepository = new AttendeeRepository(jdbcTemplate);
        userTestDataManager = new UserTestDataManager(jdbcTemplate);
        userTestDataManager.clearAttendeeRelatedTablesExceptAdmin();
    }

    @Test
    @Rollback(true)
    public void shouldSaveAttendee() {
        UUID conferenceId = UUID.randomUUID();
        jdbcTemplate.execute(String.format("INSERT INTO zombie_conference VALUES(cast('%s' AS uuid),'name','topic','description'," +
                "'venue','start','end',5);", conferenceId.toString()));
        jdbcTemplate.execute("INSERT INTO zombie_users VALUES('username', 'password1', 2, 'Attendee', 'attendee@zombie.com')");
        Attendee attendee = new Attendee(new User("username", Role.ATTENDEE, "Attendee", "attendee@zombie.com"), "dob", "country", null, null, null, true, true);

        AttendeeRepository attendeeRepository = new AttendeeRepository(jdbcTemplate);
        boolean isAttendeeSaved = attendeeRepository.insertAttendeeInfo(attendee, conferenceId);
        assertThat(isAttendeeSaved, is(true));
    }

}
