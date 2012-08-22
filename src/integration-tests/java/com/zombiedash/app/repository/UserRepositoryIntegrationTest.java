package com.zombiedash.app.repository;

import java.util.List;
import com.zombiedash.app.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-application-context.xml")
public class UserRepositoryIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void shouldCheckIfAdminUserIsRetrieved() throws Exception{
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User adminUser = userRepository.retrieveAdminUser();
        assertTrue(adminUser.authenticate("admin", "Welcome1"));
    }

    @Test
    public void shouldRetrieveUser() {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        User result = userRepository.retrieveUser("admin");
        assertThat(result, is(new User("admin", "Welcome1")));
    }

    @Test
    public void shouldRetrieveAllUsers() {
        UserRepository userRepository = new UserRepository(jdbcTemplate);
        List<User> result = userRepository.retrieveAllUsers();
        assertThat(result.get(0), is(new User("admin", "Welcome1")));
    }

}
