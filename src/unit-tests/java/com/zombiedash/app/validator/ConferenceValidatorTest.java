package com.zombiedash.app.validator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class ConferenceValidatorTest {
    private  ConferenceValidator conferenceValidator;
    private Map<String,String> model;

    @Before
    public void setUp() {
        conferenceValidator = new ConferenceValidator();
        model = new HashMap<String, String>();
    }

    @Test
    public void shouldValidateAndReturnTrueWhenAllDataEnteredIntoCreateConferenceIsCorrect() {
        Map<String,String> model = new HashMap<String, String>();
        model.put("name", "Dummy_Check");
        model.put("topic", "Dummy_Check");
        model.put("startDate","2013-01-01");
        model.put("endDate", "2013-01-04");
        model.put("description","Dummy_Check");
        model.put("venue", "Dummy_Check");
        model.put("maxAttendees", "20");
        boolean isValid = conferenceValidator.isValidData(model);
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void shouldValidateAndReturnFalseWhenDateEnteredIntoCreateConferenceIsInCorrect() {
        Map<String,String> model = new HashMap<String, String>();
        model.put("name", "Dummy_Check");
        model.put("topic", "Dummy_Check");
        model.put("startDate","2013-01-01549608");
        model.put("endDate", "2013-01-04");
        model.put("description","Dummy_Check");
        model.put("venue", "Dummy_Check");
        model.put("maxAttendees", "20");
        boolean isValid = conferenceValidator.isValidData(model);
        Assert.assertThat(isValid, is(false));
    }

    @Test
    public void shouldValidateAndReturnFalseWhenNameEnteredIntoCreateConferenceIsLong() {
        Map<String,String> model = new HashMap<String, String>();
        model.put("name", "Dummy_Check");
        model.put("topic", "Dummy_Check");
        model.put("startDate","2013-01-01549608");
        model.put("endDate", "2013-01-04");
        model.put("description","Dummy_Check");
        model.put("venue", "Dummy_Check");
        model.put("maxAttendees", "20");
        boolean isValid = conferenceValidator.isValidData(model);
        Assert.assertThat(isValid, is(false));
    }
}
