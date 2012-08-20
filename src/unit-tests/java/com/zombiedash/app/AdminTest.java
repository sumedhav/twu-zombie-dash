package com.zombiedash.app;

import com.zombiedash.app.model.Admin;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminTest {
    @Test
    public void shouldBeSingleton() throws Exception {
        Admin admin = Admin.getCurrentInstance();
        Admin otherAdmin = Admin.getCurrentInstance();
        assertThat(admin, is(otherAdmin));
    }

    @Test
    public void shouldBeInstantiated() throws Exception {
        Admin admin = Admin.getCurrentInstance();
        assertThat(admin,notNullValue());
    }

    @Test
    public void shouldAuthenticateWithProperCredentials() throws Exception {
        Admin admin = Admin.getCurrentInstance();
        Boolean loginResult = admin.authenticate("admin","Welcome1".toCharArray());
        assertThat(loginResult,is(true));
    }

    @Test
    public void shouldNotAuthenticateWithoutProperCredentials() throws Exception {
        Admin admin = Admin.getCurrentInstance();
        Boolean badUserName = admin.authenticate("administrator","Welcome1".toCharArray());
        Boolean badPassword = admin.authenticate("admin","abc".toCharArray());
        assertThat(badUserName,is(false));
        assertThat(badPassword,is(false));
    }
}
