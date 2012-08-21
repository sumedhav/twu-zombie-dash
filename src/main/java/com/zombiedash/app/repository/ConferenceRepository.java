package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ConferenceRepository {
    private Conference conference;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConferenceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveConference() {
        String sql = "IF (EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'TheSchema' AND  TABLE_NAME = 'TheTable'))\n" +
                "BEGIN\n" +
                "    --Do Stuff\n" +
                "END";
    }


    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
