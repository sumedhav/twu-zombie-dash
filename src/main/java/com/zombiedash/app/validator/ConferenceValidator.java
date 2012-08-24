package com.zombiedash.app.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

public class ConferenceValidator {
  public boolean isValidNumber(String conferenceMaxAttendees, Map<String, String> model) {
    try {
      Integer theInt = Integer.parseInt(conferenceMaxAttendees);
      if (theInt > 0) return true;
    } catch (NumberFormatException e) {
    }
    model.put("numberError", "invalid data");
    return false;
  }

  public boolean isNotEmpty(String name, String topic, String description,
                            String venue, String startDate, String endDate,
                            String maxAttendee, Map<String, String> model) {
    if (name.isEmpty() ||
        topic.isEmpty() ||
        description.isEmpty() ||
        venue.isEmpty() ||
        startDate.isEmpty() ||
        endDate.isEmpty() ||
        maxAttendee.isEmpty()) {
      model.put("errorString", "ALL FIELDS ARE COMPULSORY");
      return false;
    }
    return true;
  }

  public boolean isValidDate(String date, Map<String, String> model, String errorName) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    dateFormat.setLenient(false);
    try {
      return (null != dateFormat.parse(date));
    } catch (ParseException e) {
      model.put(errorName, "Date must be in yyyy-MM-dd format");
      return false;
    }
  }
}
