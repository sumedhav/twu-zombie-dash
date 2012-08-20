package com.zombiedash.app;

import com.zombiedash.app.model.Admin;
import org.junit.Test;

public class AdminTest {
    @Test
    public void shouldAuthenticateWithProperCredentials() throws Exception {
        Admin admin =  new Admin("admin","Welcome1".toCharArray());
        admin.authenticate("admin","Welcome1".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperUserName() throws Exception {
        Admin admin =  new Admin("admin","Welcome1".toCharArray());
        admin.authenticate("administrator","Welcome1".toCharArray());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionWithoutProperPassword() throws Exception {
        Admin admin =  new Admin("admin","Welcome1".toCharArray());
       admin.authenticate("admin","abc".toCharArray());
    }
}
