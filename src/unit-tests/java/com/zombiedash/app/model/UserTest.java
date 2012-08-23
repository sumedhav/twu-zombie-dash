package com.zombiedash.app.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    @Test
    public void shouldCheckIfUserIsAGameDesigner() {
        Role userRole = new User("designer","Password1").getUserRole();
        assertThat(userRole, is(Role.GAME_DESIGNER));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfPasswordValidationFailsFirstExample() throws Exception {
        User user1 = new User("user", "password", Role.GAME_DESIGNER, "Name", "email@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfPasswordValidationFailsSecondExample() throws Exception {
        User user1 = new User("user", "welcome", Role.GAME_DESIGNER, "Name", "email@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfPasswordValidationFailsThirdExample() throws Exception {
        User user1 = new User("user", "pass", Role.GAME_DESIGNER, "Name", "email@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfPasswordValidationFailsFourthExample() throws Exception {
        User user1 = new User("user", "11111", Role.GAME_DESIGNER, "Name", "email@email.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsFirstExample() throws Exception {
        User user1 = new User("user", "Welcome1", Role.GAME_DESIGNER, "Name", "abcl.com");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsSecondExample() throws Exception {
        User user1 = new User("user", "Welcome1", Role.GAME_DESIGNER, "Name", "email@email");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsThirdExample() throws Exception {
        User user1 = new User("user", "Welcome1", Role.GAME_DESIGNER, "Name", "abclcom");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsFourthExample() throws Exception {
        User user1 = new User("user", "Welcome1", Role.GAME_DESIGNER, "Name", "abcl@.com");

    }
}
