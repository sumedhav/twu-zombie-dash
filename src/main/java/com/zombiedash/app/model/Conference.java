package com.zombiedash.app.model;


public class Conference {
    private String name;
    private String topic;
    private String description;
    private String venue;
    private String startDate;
    private String endDate;
    private int maxAttendee;
    private Speaker speaker;
    private Organiser organiser;


    public Conference(String name,
                      String topic,
                      String description,
                      String venue,
                      String startDate,
                      String endDate,
                      int maxAttendee,
                      Speaker speaker,
                      Organiser organiser) {
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendee = maxAttendee;
        this.speaker = speaker;
        this.organiser = organiser;
    }
}
