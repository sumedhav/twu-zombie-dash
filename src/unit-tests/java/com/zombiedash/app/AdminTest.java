package com.zombiedash.app;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminTest {
    @Test
    public void shouldBeSingleton() throws Exception {
        Admin admin = Admin.getCurrentInstance();
        Admin otherAdmin = Admin.getCurrentInstance();
        assertThat(admin, is(otherAdmin));
    }
}
