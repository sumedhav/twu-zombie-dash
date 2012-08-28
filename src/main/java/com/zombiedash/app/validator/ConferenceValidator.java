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
      else {
          model.put("numberError","Must be a positive integer");
          return false;
      }
    } catch (NumberFormatException e) {
        if (!conferenceMaxAttendees.isEmpty())
            model.put("numberError", "Must be a positive integer");
        return false;
    }
  }

  public boolean isValidDate(String date, Map<String, String> model, String errorName) {
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    dateFormat.setLenient(false);
    try {
      return (null != dateFormat.parse(date));
    } catch (ParseException e) {
        if (!date.isEmpty()) model.put(errorName, "Must be in yyyy-mm-dd format");
        return false;
    }
  }

    public boolean isFieldCompleted(String field, Map<String, String> model, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"*");
            model.put("errorString","All (*) fields are compulsory");
            return false;
        }
        else return true;
    }

    public boolean isDataValid(Map<String,String> model) {
        boolean validDataFlag = this.isValidNumber(model.get("maxAttendees"), model);
        validDataFlag &= this.isValidDate(model.get("startDate"), model, "startDateError");
        validDataFlag &= this.isValidDate(model.get("endDate"), model, "endDateError");

        validDataFlag &= this.isFieldCompleted(model.get("name"),model,"nameFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("topic"),model,"topicFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("description"),model,"descriptionFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("venue"),model,"venueFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("startDate"),model,"startDateFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("endDate"),model,"endDateFieldMissing");
        validDataFlag &= this.isFieldCompleted(model.get("maxAttendees"),model,"maxAttendeesFieldMissing");

        return validDataFlag;
    }
}
