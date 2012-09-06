package com.zombiedash.app.validator;

import com.zombiedash.app.model.Conference;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConferenceForm {

    private String conf_name;
    private String conf_topic;
    private String conf_venue;
    private String conf_start_date;
    private String conf_end_date;
    private String conf_description;
    private String conf_max_attendees;

    private HashMap<String, String> model = new HashMap<String, String>();

    private boolean isValidNumber(String conferenceMaxAttendees) {
        try {
            Integer theInt = Integer.parseInt(conferenceMaxAttendees);
            if (theInt > 0) return true;
            else {
                model.put("numberError","Please enter a positive integer.");
                return false;
            }
        } catch (NumberFormatException e) {
            if (!conferenceMaxAttendees.isEmpty())
                model.put("numberError", "Please enter a positive integer.");
            return false;
        }
    }



    private boolean isValidDate(String rawDate, String errorName) {
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
            model.put(errorName,"Please enter a current or future date.");
            return false;
        } catch (ParseException e) {
            if (!rawDate.isEmpty()) model.put(errorName, "Enter a date in yyyy-mm-dd format.");
            return false;
        }
    }

    private boolean isCompletedField(String field, String fieldMissingErrorName) {
        if (field.isEmpty()) {
            model.put(fieldMissingErrorName,"You can't leave this field empty.");
            return false;
        }
        return true;
    }

    public boolean isValidData() {
        conf_name = conf_name.trim();
        conf_topic = conf_topic.trim();
        conf_description = conf_description.trim();
        conf_venue = conf_venue.trim();
        conf_max_attendees = conf_max_attendees.trim();

        boolean validDataFlag = this.isValidNumber(conf_max_attendees);
        validDataFlag &= this.isValidDate(conf_start_date, "startDateError");
        validDataFlag &= this.isValidDate(conf_end_date, "endDateError");
        validDataFlag &= this.isEndAfterStartDate(conf_start_date, conf_end_date, "endDateError");

        validDataFlag &= this.isCompletedField(conf_name, "nameFieldMissing");
        validDataFlag &= this.isCompletedField(conf_topic, "topicFieldMissing");
        validDataFlag &= this.isCompletedField(conf_description, "descriptionFieldMissing");
        validDataFlag &= this.isCompletedField(conf_venue, "venueFieldMissing");
        validDataFlag &= this.isCompletedField(conf_start_date, "startDateFieldMissing");
        validDataFlag &= this.isCompletedField(conf_end_date, "endDateFieldMissing");
        validDataFlag &= this.isCompletedField(conf_max_attendees, "maxAttendeesFieldMissing");

        return validDataFlag;
    }

    private boolean isEndAfterStartDate(String startDate, String endDate, String endDateError) {
        boolean isValid = (isValidDate(startDate, "") && isValidDate(endDate, ""));
        if (!isValid){
            return false;
        }
        else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date start = dateFormat.parse(startDate);
                Date end = dateFormat.parse(endDate);
                if (end.after(start) || end.equals(start)) return true;
                model.put(endDateError, "Please enter a date that is on or after start date.");
                return false;
            } catch (ParseException e) {
                return false;
            }
        }
    }


    public Conference createConference() {
        return new Conference(1L,
                conf_name,
                conf_topic,
                conf_description,
                conf_venue,
                conf_start_date,
                conf_end_date,
                Integer.valueOf(conf_max_attendees));
    }

    public HashMap<String, String> populateModelMapWithFormValues() {
        model.put("name",conf_name);
        model.put("topic",conf_topic);
        model.put("startDate",conf_start_date);
        model.put("endDate",conf_end_date);
        model.put("description",conf_description);
        model.put("venue",conf_venue);
        model.put("maxAttendees", conf_max_attendees);

        return model;
    }

    public void setConf_name(String conf_name) {
        this.conf_name = conf_name;
    }

    public void setConf_topic(String conf_topic) {
        this.conf_topic = conf_topic;
    }

    public void setConf_venue(String conf_venue) {
        this.conf_venue = conf_venue;
    }

    public void setConf_start_date(String conf_start_date) {
        this.conf_start_date = conf_start_date;
    }

    public void setConf_end_date(String conf_end_date) {
        this.conf_end_date = conf_end_date;
    }

    public void setConf_description(String conf_description) {
        this.conf_description = conf_description;
    }

    public void setConf_max_attendees(String conf_max_attendees) {
        this.conf_max_attendees = conf_max_attendees;
    }
}
