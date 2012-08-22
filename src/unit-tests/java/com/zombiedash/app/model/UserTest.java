package com.zombiedash.app.model;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTest {

    @Test
    public void shouldAuthenticateWithProperCredentials() throws Exception {
        new User("user","password".toCharArray()).authenticate("user", "password".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperUserName() throws Exception {
        new User("user","password".toCharArray()).authenticate("wrongUsername", "password".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperPassword() throws Exception {
        new User("user","password".toCharArray()).authenticate("user", "abc".toCharArray());
    }

    @Test
    public void authenticatedUserShouldBeLoggedIn() throws Exception {
        User user = new User("admin","password".toCharArray());
        user.authenticate("admin", "password".toCharArray());
        Boolean logStatus = user.isLoggedIn();
        assertThat(logStatus,is(true));
    }

    @Test
    public void createdUserShouldNotBeLoggedIn() throws Exception {
        Boolean logStatus = new User("admin","password".toCharArray()).isLoggedIn();
        assertThat(logStatus,is(false));
    }

    @Test
    public void shouldCheckIfUserIsAGameDesigner() {
        Role userRole = new User("designer","password".toCharArray()).getUserRole();
        assertThat(userRole, is(Role.GAME_DESIGNER));
    }
}
