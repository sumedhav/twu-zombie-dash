package com.zombiedash.app.test.matchers;

import com.zombiedash.app.model.Conference;
import org.apache.commons.lang.StringUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ConferenceMatcher extends BaseMatcher <Conference>{

    private Long id;
    private String name;
    private String topic;
    private String description;
    private String venue;
    private String startDate;
    private String endDate;
    private int maxAttendees;
    private Conference actualConference;

    public ConferenceMatcher(Long id, String name, String topic, String description, String venue, String startDate, String endDate, int maxAttendees) {
        this.id = id;
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendees = maxAttendees;
    }

    @Override
    public boolean matches(Object item) {
        actualConference = (Conference) item;
        return id == actualConference.getId() &&
                name.equals(actualConference.getName()) &&
                topic.equals(actualConference.getTopic()) &&
                description.equals(actualConference.getDescription()) &&
                venue.equals(actualConference.getVenue()) &&
                startDate.equals(actualConference.getStartDate()) &&
                endDate.equals(actualConference.getEndDate()) &&
                maxAttendees == actualConference.getMaxAttendee();
    }

    @Override
    public void describeTo(Description description) {
        String expectedConferenceAttributes = conferenceAttributes(id, name, topic, this.description, venue, startDate, endDate, maxAttendees);
        String actualConferenceAttributes = conferenceAttributes(actualConference.getId(),
                actualConference.getName(),
                actualConference.getTopic(),
                actualConference.getDescription(),
                actualConference.getVenue(),
                actualConference.getStartDate(),
                actualConference.getEndDate(),
                actualConference.getMaxAttendee());
        description.appendText("Expected user [" + expectedConferenceAttributes + "], " + "instead got user with username: [" + actualConferenceAttributes + "]");
    }

    private String conferenceAttributes(Long id,
                                  String name,
                                  String topic,
                                  String description,
                                  String venue,
                                  String startDate,
                                  String endDate,
                                  int maxAttendees) {
        return StringUtils.join(new String[]{String.valueOf(id), name, topic, description, venue, startDate, endDate, String.valueOf(maxAttendees)}, ", ");
    }

    public static Matcher<Conference> isAConferenceWith(Long id,
                                                        String name,
                                                        String topic,
                                                        String description,
                                                        String venue,
                                                        String startDate,
                                                        String endDate,
                                                        int maxAttendees) {
        return new ConferenceMatcher(id, name, topic, description, venue, startDate, endDate, maxAttendees);
    }
}
