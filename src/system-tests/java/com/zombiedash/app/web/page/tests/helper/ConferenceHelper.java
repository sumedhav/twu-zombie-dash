package com.zombiedash.app.web.page.tests.helper;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.repository.ConferenceRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.UUID;

public class ConferenceHelper {
  public static UUID insertSampleConference(ConferenceRepository conferenceRepository) {
    UUID conferenceId = UUID.randomUUID();
    conferenceRepository.insertConference(
        new Conference(conferenceId, "Java", "Java", "Java", "here", "2013-01-01", "2013-01-05", 100));
    return conferenceId;
  }

  public static void clearDatabase(JdbcTemplate jdbcTemplate) {
    jdbcTemplate.execute("DELETE zombie_option");
    jdbcTemplate.execute("DELETE zombie_question");
    jdbcTemplate.execute("DELETE zombie_task");
    jdbcTemplate.execute("DELETE zombie_conference");
  }
}
