package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class ConferenceRepository {
    public static final String SQL_CONFERENCE_INSERT = "INSERT INTO Conference values (?,?,?,?,?,?,?,?,?,?)";
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
}
