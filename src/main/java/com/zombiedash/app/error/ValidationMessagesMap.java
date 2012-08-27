package com.zombiedash.app.error;

import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public class ValidationMessagesMap {
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("validation-messages", Locale.ENGLISH);

    public String getMessageFor(String messageKey) {
        return resourceBundle.getString(messageKey);
    }
}
