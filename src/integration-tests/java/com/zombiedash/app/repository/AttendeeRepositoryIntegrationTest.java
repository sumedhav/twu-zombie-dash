package com.zombiedash.app.repository;

import com.zombiedash.app.model.Attendee;
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
    Attendee attendee = new Attendee("username","password","name","email","dob", "country");
    AttendeeRepository attendeeRepository = new AttendeeRepository(jdbcTemplate);
    Attendee attendee1 = attendeeRepository.saveAttendee(attendee, 999);
    assertThat(attendee1,is(equalTo(attendee)));
  }
}
