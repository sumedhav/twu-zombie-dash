function confirmCancel(contextPath) {
    contextPath= (contextPath==undefined) ? "": contextPath;
    if(confirm("Are you sure you want to leave this page?")) {
        location.replace(contextPath+"/zombie/admin/conference/home");
    }
}

function limitNumOfCharsInField(field, maxNumChars, errorName) {
    if (field.value.length > maxNumChars) {
        field.value = field.value.substring(0, maxNumChars);
        var pre = "Trying to exceed the max number (";
        var post = ") of characters";
        var message = pre + maxNumChars + post;
        $(errorName).html(message);
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
  createCalendar("#conf_start_date");
  createCalendar("#conf_end_date");
})




