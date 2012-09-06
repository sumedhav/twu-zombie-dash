package com.zombiedash.app.validator;

import com.zombiedash.app.model.Conference;
import com.zombiedash.app.test.matchers.ConferenceMatcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class ConferenceFormTest {
    private ConferenceForm conferenceForm;
    private Map<String,String> model;

    @Before
    public void setUp() {
        conferenceForm = new ConferenceForm("NotNull", "NotNull", "NotNull", "NotNull", "2013-03-04", "2013-03-04", "10");
        model = new HashMap<String, String>();
    }

    @Test
    public void shouldCreateConference() throws Exception {
        assertThat(conferenceForm.createConference(),
                ConferenceMatcher.isAConferenceWith(1L, "NotNull", "NotNull", "NotNull", "NotNull", "2013-03-04", "2013-03-04", 10));
    }

    @Test
    public void shouldPopulateModelMapWithFormValues() throws Exception {
        HashMap<String, String> model = conferenceForm.populateModelMapWithFormValues();
        assertThat(model.get("name"), is("NotNull"));
        assertThat(model.get("topic"), is("NotNull"));
        assertThat(model.get("startDate"), is("2013-03-04"));
        assertThat(model.get("endDate"), is("2013-03-04"));
        assertThat(model.get("venue"), is("NotNull"));
        assertThat(model.get("description"), is("NotNull"));
        assertThat(model.get("maxAttendees"), is("10"));
    }

    @Test
    public void shouldValidateAndReturnTrueWhenAllDataEnteredIntoCreateConferenceIsCorrect() {
        ConferenceForm conferenceForm1 = new ConferenceForm("Dummy_Check", "Dummy_Check", "Dummy_Check","Dummy_Check", "2013-01-01", "2013-01-04", "20");
        boolean isValid = conferenceForm1.isValidData();
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void shouldValidateAndReturnFalseWhenDateEnteredIntoCreateConferenceIsInCorrect() {
        ConferenceForm conferenceForm1 = new ConferenceForm("Dummy_Check", "Dummy_Check", "Dummy_Check","Dummy_Check", "2013-01-01549608", "2013-01-04", "20");
        boolean isValid = conferenceForm1.isValidData();
        Assert.assertThat(isValid, is(false));
    }

}
