package com.zombiedash.app.repository;

import com.zombiedash.app.model.Attendee;
import com.zombiedash.app.model.Role;
import com.zombiedash.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class AttendeeRepository {
    public static final String INSERT_INTO_ATTENDEE_INFO_TABLE = "INSERT INTO zombie_attendee_info VALUES(?,?,?,?,?,?,?,?,?)";

    public static final String SELECT_ATTENDEE = "SELECT * FROM zombie_attendees as attendee," +
            "zombie_users as user" +
            "WHERE user.username= ? AND attendee.conference_ID = ? AND" +
            "user.username=attendee.username";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AttendeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean insertAttendeeInfo(Attendee attendee, UUID conference_id) {
             jdbcTemplate.update(INSERT_INTO_ATTENDEE_INFO_TABLE,
                    attendee.getUsername(),
                    attendee.getDob(),
                    attendee.getCountry(),
                    attendee.getPhoneNo(),
                    attendee.getAddress(),
                    attendee.getZipcode(),
                    attendee.getProductsMailingConfirmation(),
                    attendee.getAdsConfirmation(),
                    conference_id);
            return true;
    }

    public Attendee fetchAttendee(String username, String conference) {
        Object[] arg = new Object[]{username, conference};
        List<Attendee> attendee = jdbcTemplate.query(SELECT_ATTENDEE, arg, new RowMapper<Attendee>() {
            @Override
            public Attendee mapRow(ResultSet resultSet, int i) throws SQLException {
                return new Attendee(
                        new User(
                                resultSet.getString("username"),
                                Role.ATTENDEE,
                                resultSet.getString("name"),
                                resultSet.getString("email")),
                        resultSet.getString("dob"),
                        resultSet.getString("country"),
                        resultSet.getString("phoneNo"),
                        resultSet.getString("address"),
                        resultSet.getString("zipcode"),
                        resultSet.getBoolean("productMails"),
                        resultSet.getBoolean("adsMails"));
            }
        });
        return attendee.get(0);
    }
}
