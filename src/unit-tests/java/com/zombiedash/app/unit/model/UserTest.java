package com.zombiedash.app.unit.model;

import com.zombiedash.app.model.User;
import org.junit.Test;

public class UserTest {
    @Test
    public void shouldAuthenticateWithProperCredentials() throws Exception {
        User user =  new User("user","Welcome1".toCharArray());
        user.authenticate("user", "Welcome1".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperUserName() throws Exception {
        User user =  new User("user","Welcome1".toCharArray());
        user.authenticate("administrator","Welcome1".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperPassword() throws Exception {
        User user =  new User("user","Welcome1".toCharArray());
       user.authenticate("user","abc".toCharArray());
    }
}
