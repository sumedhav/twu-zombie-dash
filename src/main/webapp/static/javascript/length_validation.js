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