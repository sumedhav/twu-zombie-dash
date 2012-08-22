package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ConferenceRepository {
  public static final String SQL_CONFERENCE_INSERT = "INSERT INTO Conference values (?,?,?,?,?,?,?,?,?,?)";
  public static final String SQL_CONFERENCE_SELECT = "SELECT * FROM Conference WHERE name = ?";
  public static final String SQL_CONFERENCE_SELECT_ALL = "SELECT * FROM Conference";
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

  public List<Conference> showAllConferences() {
    List<Map<String,Object>> conferenceSQLCollection = jdbcTemplate.queryForList(SQL_CONFERENCE_SELECT_ALL);
    ArrayList<Conference> conferences = new ArrayList<Conference>();
    SimpleDateFormat dateformatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    for (Map<String, Object> resultRow : conferenceSQLCollection) {
      conferences.add(
          new Conference(
              (String) resultRow.get("name"),
              (String) resultRow.get("topic"),
              (String) resultRow.get("description"),
              (String) resultRow.get("venue"),
              dateformatYYYYMMDD.format((Date) resultRow.get("start_date")),
              dateformatYYYYMMDD.format((Date) resultRow.get("end_date")),
              (Integer) resultRow.get("max_attendee"),
              (String) resultRow.get("organiser_name"),
              (String) resultRow.get("organiser_contact_no"),
              (String) resultRow.get("organiser_email")));
    }
    return conferences;
  }
}
