package com.zombiedash.app.helper;

import com.zombiedash.app.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

import java.util.UUID;

public class UserTestDataManager {
    private JdbcTemplate jdbcTemplate;
    private String INSERT_USER = "INSERT INTO zombie_users values (?,?,?,?,?)";
    private String INSERT_ATTENDEE_INFO = "INSERT INTO zombie_attendee_info values (?,?,?,?,?,?,?,?,?)";
    private String INSERT_CONFERENCE = "INSERT INTO zombie_conference values (?,?,?,?,?,?,?,?)";


    private PasswordEncoder passwordEncoder = new ShaPasswordEncoder(512);
    private static final Object SALT = null;


    public void clearAttendeeRelatedTablesExceptAdmin(){
        jdbcTemplate.execute("DELETE from zombie_users WHERE username!='admin'");
        jdbcTemplate.execute("DELETE zombie_conference");
        jdbcTemplate.execute("DELETE zombie_attendee_info");
    }

    public UserTestDataManager(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

    public void insertUser(String uuid,String userName,String password,Role role,String name,String email){
        jdbcTemplate.update(INSERT_USER, userName, passwordEncoder.encodePassword(password, SALT), role.getVal(), name,email);
    }
    public void insertAttendeeWithGenericConference(String uuid, String userName, String password, String name, String email, String dob, String country, String phoneNo, String address, String zipcode){
        jdbcTemplate.update(INSERT_USER,userName, passwordEncoder.encodePassword(password, SALT), Role.ATTENDEE.getVal(), name,email);
        UUID confId=insertConference();
        jdbcTemplate.update(INSERT_ATTENDEE_INFO, userName,dob,country,phoneNo,address,zipcode,false,false,confId);
    }

    public void insertAttendeeForConference(UUID confId,String username, String password){
        jdbcTemplate.update(INSERT_USER, username, passwordEncoder.encodePassword(password, SALT), Role.ATTENDEE.getVal(),"name","email@email.com");
        jdbcTemplate.update(INSERT_ATTENDEE_INFO, username,"1990-01-01","INDIA",null,null,null,false,false,confId);
    }

    public void insertAttendeeWithGenericConference(String userName, String password){
        jdbcTemplate.update(INSERT_USER, userName, passwordEncoder.encodePassword(password, SALT), Role.ATTENDEE.getVal(),"name","email@email.com");
        UUID confId=insertConference();
        jdbcTemplate.update(INSERT_ATTENDEE_INFO, userName,"1990-01-01","INDIA",null,null,null,false,false,confId);
    }

    private UUID insertConference() {
        UUID uuid=UUID.randomUUID();
        jdbcTemplate.update(INSERT_CONFERENCE,uuid,"confName","confTopic","confDescription","confVenue","2013-01-01","2014-01-01",1);
        return uuid;
    }

    public void insertUserWithUsername(String userName)
    {
        jdbcTemplate.update(INSERT_USER,userName,"password12",Role.GAME_DESIGNER.getVal(),"yahya","email@email.com");
    }
}
