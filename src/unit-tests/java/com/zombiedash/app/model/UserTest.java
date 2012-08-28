package com.zombiedash.app.model;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class UserTest {


    @Test
    public void shouldCheckIfUserIsAGameDesigner() {
        Role userRole = new User("designer", Role.GAME_DESIGNER, "Name", "email@email.com").getUserRole();
        assertThat(userRole, is(Role.GAME_DESIGNER));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsFirstExample() throws Exception {
        User user1 = new User("user", Role.GAME_DESIGNER, "Name", "abcl.com");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsSecondExample() throws Exception {
        User user1 = new User("user", Role.GAME_DESIGNER, "Name", "email@email");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsThirdExample() throws Exception {
        User user1 = new User("user", Role.GAME_DESIGNER, "Name", "abclcom");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfEmailValidationFailsFourthExample() throws Exception {
        User user1 = new User("user", Role.GAME_DESIGNER, "Name", "abcl@.com");

    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheBeginning() throws Exception {
        User user1 = new User(" user", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheMiddle() throws Exception {
        User user2 = new User("us  er", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowRuntimeExceptionIfUsernameHasWhitespaceInTheMiddleAndEnd() throws Exception {
        User user3 = new User("us  er ", Role.GAME_DESIGNER, "Name", "abcl@abcl.com");
    }
}
