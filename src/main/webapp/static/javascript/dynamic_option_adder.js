var count = 0;

function setCount(value) {
    count=value;
}
function getCount(){
 return ++count;
}

function redirectURL(contextPath, destinationUrl) {
    contextPath= (contextPath==undefined) ? "": contextPath;
       location.replace(contextPath+destinationUrl);
}

function createOption(){
//Need to add empty option validation error.
    var c = getCount();
    setCount(c);

    var tdata = "<tr name=\"row_" + c + "\">"
                    + "<td class=\"controls\">"
                        + "<input type=\"radio\" name=\"isCorrect\" id=\"isCorrect" + c + "\"/>"
                    + "</td>"
                    + "<td class=\"controls\">"
                        + "<input type=\"text\" name=\"optionText_" + c + "\" id=\"optionText_" + c
                            + "\" onkeyup=\"limitNumOfCharsInField( optionText_" + c + ", 10, \'#optionText_exceed_error_"
                            + c + " \');\" onkeydown=\"limitNumOfCharsInField( optionText_" + c + ", 10, \'#optionText_exceed_error_"
                            + c + " \');\"value=\"\" placeholder=\"enter option " + c + "\"/>"
                        + "<span id=\"optionText_exceed_error_" + c + "\" class=\"error\"></span>"
                    + "</td>"
                + "</tr>";

    var tdataElement = jQuery(tdata);
    jQuery('#optionTable > tbody:last').append(tdataElement);
}
