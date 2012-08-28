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
}
