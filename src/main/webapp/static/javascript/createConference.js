function limitMaxNumberOfAttendees(maxNumChars,errorName) {
  if (conf_max_attendees.value.length > maxNumChars) {
          conf_max_attendees.value = conf_max_attendees.value.substring(0, maxNumChars);
          $(errorName).html("Maximum number of attendees is 999999");
      }
      else {
          $(errorName).html("");
      }
}

function createCalendar(element) {
  var d = new Date();
  $(element).datepick({dateFormat: 'yyyy-mm-dd', alignment: 'bottomLeft', minDate: '#d', showOnFocus: true, showTrigger: '#calImg', clear: false});
}

$(document).ready(function() {
  document.conferenceForm.conf_name.focus();
  createCalendar("#conf_start_date");
  createCalendar("#conf_end_date");
})




