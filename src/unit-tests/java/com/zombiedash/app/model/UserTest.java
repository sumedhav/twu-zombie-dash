package com.zombiedash.app.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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

    @Test
    public void loggedInAdminShouldBeLoggedIn() throws Exception {
        User user = new User("user","Welcome1".toCharArray());
        user.authenticate("user","Welcome1".toCharArray());
        Boolean logStatus = user.isLoggedIn();
        assertThat(logStatus,is(true));
    }

    @Test
    public void unloggedInAdminShouldNotBeLoggedIn() throws Exception {
        User user = new User("user","Welcome1".toCharArray());
        Boolean logStatus = user.isLoggedIn();
        assertThat(logStatus,is(false));
    }
}
