package com.zombiedash.app.model;

import org.junit.Test;

import static com.zombiedash.app.model.Role.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoleTest {
    @Test
    public void shouldGenerateAttendeeRole() throws Exception {
        assertThat(Role.generateRole("attendee"), is(equalTo(ATTENDEE)));
    }

    @Test
    public void shouldGenerateGameDesignerRole() throws Exception {
        assertThat(Role.generateRole("game designer"), is(equalTo(GAME_DESIGNER)));
    }

    @Test
    public void shouldGenerateAdminRole() throws Exception {
        assertThat(Role.generateRole("admin"), is(equalTo(ADMIN)));
    }
}
