package com.zombiedash.app.model;


public class Conference {
  private String name;
  private String topic;
  private String description;
  private String venue;
  private String startDate;
  private String endDate;
  private int maxAttendee;

  public Conference(String name,
                    String topic,
                    String description,
                    String venue,
                    String startDate,
                    String endDate,
                    Integer maxAttendee) {
    this.name = name;
    this.topic = topic;
    this.description = description;
    this.venue = venue;
    this.startDate = startDate;
    this.endDate = endDate;
    this.maxAttendee = maxAttendee;
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
}
