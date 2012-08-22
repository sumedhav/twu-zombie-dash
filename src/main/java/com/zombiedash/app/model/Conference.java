package com.zombiedash.app.model;


public class Conference {
    private String name;
    private String topic;
    private String description;
    private String venue;
    private String startDate;
    private String endDate;
    private int maxAttendee;
    private String organiserName;
    private String organiserContactNumber;
    private String organiserEmail;

    public Conference(String name,
                      String topic,
                      String description,
                      String venue,
                      String startDate,
                      String endDate,
                      int maxAttendee,
                      String organiserName,
                      String organiserContactNumber,
                      String organiserEmail) {
        this.name = name;
        this.topic = topic;
        this.description = description;
        this.venue = venue;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxAttendee = maxAttendee;
        this.organiserName = organiserName;
        this.organiserContactNumber = organiserContactNumber;
        this.organiserEmail = organiserEmail;
    }

    public String getName() {
        return name;
    }

    public String getTopic() {
        return topic;
    }

    public String getDescription() {
        return description;
    }

    public String getVenue() {
        return venue;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public int getMaxAttendee() {
        return maxAttendee;
    }

    public String getOrganiserName() {
        return organiserName;
    }

    public String getOrganiserContactNumber() {
        return organiserContactNumber;
    }

    public String getOrganiserEmail() {
        return organiserEmail;
    }
}
