package com.zombiedash.app.validator;

import com.zombiedash.app.forms.ConferenceForm;
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
        conferenceForm = new ConferenceForm();
        conferenceForm.setConf_name("NotNull");
        conferenceForm.setConf_topic("NotNull");
        conferenceForm.setConf_description("NotNull");
        conferenceForm.setConf_venue("NotNull");
        conferenceForm.setConf_start_date("2013-03-04");
        conferenceForm.setConf_end_date("2013-03-04");
        conferenceForm.setConf_max_attendees("10");
        model = new HashMap<String, String>();
    }

    @Test
    public void shouldCreateConference() throws Exception {
        Conference conference = conferenceForm.createConference();
        assertThat(conference,
                ConferenceMatcher.isAConferenceWith(conference.getId(), "NotNull", "NotNull", "NotNull", "NotNull", "2013-03-04", "2013-03-04", 10));
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
        conferenceForm.setConf_name("NotNull");
        conferenceForm.setConf_topic("NotNull");
        conferenceForm.setConf_description("NotNull");
        conferenceForm.setConf_venue("NotNull");
        conferenceForm.setConf_start_date("2013-03-04");
        conferenceForm.setConf_end_date("2013-03-04");
        conferenceForm.setConf_max_attendees("10");
        boolean isValid = conferenceForm.isValidData();
        Assert.assertThat(isValid, is(true));
    }

    @Test
    public void shouldValidateAndReturnFalseWhenDateEnteredIntoCreateConferenceIsInCorrect() {
        conferenceForm.setConf_name("NotNull");
        conferenceForm.setConf_topic("NotNull");
        conferenceForm.setConf_description("NotNull");
        conferenceForm.setConf_venue("NotNull");
        conferenceForm.setConf_start_date("2013-03-01549608");
        conferenceForm.setConf_end_date("2013-03-04");
        conferenceForm.setConf_max_attendees("10");
        boolean isValid = conferenceForm.isValidData();
        Assert.assertThat(isValid, is(false));
    }

}
