package com.zombiedash.app.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserTest {
    @Test
    public void shouldAuthenticateWithProperCredentials() throws Exception {
        User user =  new User("user","Welcome1");
        assertTrue(user.authenticate("user", "Welcome1"));
    }

    @Test
    public void shouldNotAuthenticateWithoutProperUserName() throws Exception {
        User user =  new User("user","Welcome1");
        assertFalse(user.authenticate("administrator", "Welcome1"));
    }

    @Test
    public void shouldNotAuthenticateWithoutProperPassword() throws Exception {
        User user =  new User("user","Welcome1");
       assertFalse(user.authenticate("user", "abc"));
    }
}
