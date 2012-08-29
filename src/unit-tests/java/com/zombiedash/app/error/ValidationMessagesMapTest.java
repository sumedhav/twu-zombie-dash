package com.zombiedash.app.error;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class ValidationMessagesMapTest {

    @Test
    public void shouldGetAMessageForAKey() throws Exception {
        ValidationMessagesMap validationMessagesMap = new ValidationMessagesMap();
        String message = validationMessagesMap.getMessageFor("invalidUserName");
        assertThat(message, is(equalTo("The Username must have 5-40 alphanumeric characters and no whitespaces.")));
    }
}
