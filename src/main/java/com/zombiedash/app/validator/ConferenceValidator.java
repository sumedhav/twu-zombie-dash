package com.zombiedash.app.validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public boolean isValidDate(String rawDate, Map<String, String> model, String errorName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            String [] datesData = rawDate.split("-");
            Date date = dateFormat.parse(rawDate);
            Date currentDate = new Date();

            if( datesData[0].length()!=4 || datesData[1].length()!=2|| datesData[2].length()!=2) {
                throw new ParseException("Invalid year/month/day format", 1);
            }

            if (currentDate.before(date) || dateFormat.format(currentDate).equals(rawDate)) {
                return true;
            }
            model.put(errorName,"Must be a current or future date");
            return false;
        } catch (ParseException e) {
            if (!rawDate.isEmpty()) model.put(errorName, "Must be valid date in yyyy-mm-dd format");
            return false;
        }
    }

    public boolean isCompletedField(String field, Map<String, String> model, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"*");
            model.put("errorString","All (*) fields are compulsory");
            return false;
        }
        else return true;
    }

    public boolean isValidData(Map<String, String> model) {
        boolean validDataFlag = this.isValidNumber(model.get("maxAttendees"), model);
        validDataFlag &= this.isValidDate(model.get("startDate"), model, "startDateError");
        validDataFlag &= this.isValidDate(model.get("endDate"), model, "endDateError");
        validDataFlag &= this.isEndAfterStartDate(model.get("startDate"), model.get("endDate"), model,"endDateError");

        validDataFlag &= this.isCompletedField(model.get("name"), model, "nameFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("topic"), model, "topicFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("description"), model, "descriptionFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("venue"), model, "venueFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("startDate"), model, "startDateFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("endDate"), model, "endDateFieldMissing");
        validDataFlag &= this.isCompletedField(model.get("maxAttendees"), model, "maxAttendeesFieldMissing");

        return validDataFlag;
    }


    public boolean isEndAfterStartDate(String startDate, String endDate, Map<String, String> model, String endDateError) {
        boolean isValid = (isValidDate(startDate, model, "") && isValidDate(endDate, model, ""));
        if (!isValid){
            return false;
        }
        else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date start = dateFormat.parse(startDate);
                Date end = dateFormat.parse(endDate);
                if (end.after(start) || end.equals(start)) return true;
                model.put(endDateError, "Must be on or after start date");
                return false;
            } catch (ParseException e) {
                return false;
            }
        }
    }

}
