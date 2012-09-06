package com.zombiedash.app.repository;

import com.zombiedash.app.model.Conference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ConferenceRepository {
    public static final String SQL_CONFERENCE_INSERT = "INSERT INTO zombie_conference values (?,?,?,?,?,?,?,?)";
    public static final String SQL_CONFERENCE_SELECT = "SELECT * FROM zombie_conference WHERE ID = ?";
    public static final String SQL_COUNT_CONFERENCE = "SELECT COUNT(*) FROM zombie_conference WHERE ID = ?";
    public static final String SQL_CONFERENCE_SELECT_ALL = "SELECT * FROM zombie_conference";
    public static final String SQL_CONFERENCE_NUM_ENTRIES = "SELECT COUNT (*) FROM zombie_conference";
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConferenceRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer saveConference(Conference conference) {
        int id = jdbcTemplate.queryForInt(SQL_CONFERENCE_NUM_ENTRIES) + 1;
        return jdbcTemplate.update(SQL_CONFERENCE_INSERT,
                id,
                conference.getName(),
                conference.getTopic(),
                conference.getDescription(),
                conference.getVenue(),
                conference.getStartDate(),
                conference.getEndDate(),
                conference.getMaxAttendee());
    }

    public boolean isConferencePresent(long conferenceID) {
        if (jdbcTemplate.queryForInt(SQL_COUNT_CONFERENCE,conferenceID) == 1)
            return true;
        else
            return false;
    }

    public Conference showConference(Long conferenceID) {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(SQL_CONFERENCE_SELECT,conferenceID);
        rowSet.first();
        return new Conference(rowSet.getLong(1),
                rowSet.getString(2),
                rowSet.getString(3),
                rowSet.getString(4),
                rowSet.getString(5),
                rowSet.getString(6),
                rowSet.getString(7),
                rowSet.getInt(8));
    }

    public List<Conference> showAllConferences() {
        List<Map<String,Object>> conferenceSQLCollection = jdbcTemplate.queryForList(SQL_CONFERENCE_SELECT_ALL);
        ArrayList<Conference> conferences = new ArrayList<Conference>();
        for (Map<String, Object> resultRow : conferenceSQLCollection) {
            conferences.add(
                    new Conference(
                            (Long) resultRow.get("id"),
                            (String) resultRow.get("name"),
                            (String) resultRow.get("topic"),
                            (String) resultRow.get("description"),
                            (String) resultRow.get("venue"),
                            (String) resultRow.get("start_date"),
                            (String) resultRow.get("end_date"),
                            (Integer) resultRow.get("max_attendee")));
        }
        return conferences;
    }
}
