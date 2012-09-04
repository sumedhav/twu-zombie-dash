package com.zombiedash.app.repository;

import com.zombiedash.app.model.Attendee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AttendeeRepository {
  public static final String INSERT_INTO_ATTENDEE_VALUES = "insert into attendee values(?,?,?,?,?,?,?)";
  public static final String SELECT_ATTENDEE = "SELECT * FROM attendee WHERE username= ? AND conference = ?";
  private JdbcTemplate jdbcTemplate;
  public AttendeeRepository(JdbcTemplate jdbcTemplate) {

    this.jdbcTemplate = jdbcTemplate;
  }

  public Attendee saveAttendee(Attendee attendee) {
    jdbcTemplate.update(INSERT_INTO_ATTENDEE_VALUES,
        attendee.getUsername(),
        attendee.getPassword(),
        attendee.getName(),
        attendee.getEmail(),
        attendee.getDob(),
        attendee.getCountry(),
        attendee.getConference());
    return attendee;
  }

  public Attendee getAttendee(String username, String conference) {
    Object[] arg = new Object[]{username,conference};
    List<Attendee> attendee = jdbcTemplate.query(SELECT_ATTENDEE,arg,new RowMapper<Attendee>() {
      @Override
      public Attendee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Attendee(
            resultSet.getString("username"),
            resultSet.getString("password"),
            resultSet.getString("name"),
            resultSet.getString("email"),
            resultSet.getString("dob"),
            resultSet.getString("conference"),
            resultSet.getString("country"));
      }
    });
    return attendee.get(0);
  }
}
