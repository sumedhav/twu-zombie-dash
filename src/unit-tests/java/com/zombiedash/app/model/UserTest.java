package com.zombiedash.app.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTest {


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

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheBeginning() throws Exception {
        User user1 = new User(" user", "Welcome1", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheMiddle() throws Exception {
        User user2 = new User("us  er", "Welcome1", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheMiddleAndEnd() throws Exception {
        User user3 = new User("us  er ", "Welcome1", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }
}
