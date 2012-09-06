package com.zombiedash.app.repository;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class AttendeeRepositoryIntegrationTest {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void shouldSaveAttendee() {
    jdbcTemplate.execute(String.format("INSERT INTO zombie_conference VALUES('999','name','topic','description','venue','start','end',5);"));
    Attendee attendee = new Attendee(new User("username", Role.ATTENDEE,"name","email"),"dob", "country",null,null,null);
    AttendeeRepository attendeeRepository = new AttendeeRepository(jdbcTemplate);
    boolean isAttendeeSaved = attendeeRepository.saveAttendee(attendee,"password12",999);
    assertThat(isAttendeeSaved,is(true));
  }

}
