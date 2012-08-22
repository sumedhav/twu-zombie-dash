package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class ConferenceRepository {
  public static final String SQL_CONFERENCE_INSERT = "INSERT INTO Conference values (?,?,?,?,?,?,?,?,?,?)";
  public static final String SQL_CONFERENCE_SELECT = "SELECT * FROM Conference WHERE name = ?";

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  public ConferenceRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Integer saveConference(Conference conference) {
    return jdbcTemplate.update(SQL_CONFERENCE_INSERT,
        conference.getName(),
        conference.getTopic(),
        conference.getDescription(),
        conference.getVenue(),
        conference.getStartDate(),
        conference.getEndDate(),
        conference.getMaxAttendee(),
        conference.getOrganiserName(),
        conference.getOrganiserContactNumber(),
        conference.getOrganiserEmail());
  }

  public Conference showConference(String conferenceName) {
    SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_CONFERENCE_SELECT,conferenceName);
    rowSet.first();
    return new Conference(rowSet.getString(1),
        rowSet.getString(2),
        rowSet.getString(3),
        rowSet.getString(4),
        rowSet.getString(5),
        rowSet.getString(6),
        rowSet.getInt(7),
        rowSet.getString(8),
        rowSet.getString(9),
        rowSet.getString(10));
  }
}
