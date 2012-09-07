function createCalendar(element) {
  var d = new Date();
  $(element).datepick({dateFormat: 'yyyy-mm-dd', alignment: 'bottomLeft', minDate: '1900-01-01', maxDate: '#d', showOnFocus: true, showTrigger: '#calImg', clear: false});
}

$(document).ready(function() {
  createCalendar("#dob");
})