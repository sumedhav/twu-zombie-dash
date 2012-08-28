<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Conference Page</title>
<script type="text/javascript">

    function confirmCancel() {
        if(confirm("Are you sure you want to leave this page?")) {
            location.replace("${pageContext.request.contextPath}/zombie/admin/conference/home");
        }
    }

    function limitNameField(field, limitCount, limitNum) {
        if (field.value.length > limitNum) {
            field.value = field.value.substring(0,limitNum);
        }
        else {
           limitCount.value = limitNum - field.value.length;
        }
    }


</script>

</head>
<body onload='document.conferenceCreationForm.conf_name.focus();'>
	<h3>Create Conference: Enter Details</h3>
	 <h4> <FONT color="red">${model.errorString}</FONT></h4>

	<form name='conferenceCreationForm' action="${pageContext.request.contextPath}/zombie/admin/conference/submit" method='POST'>

		<table>
			<tr>
				<td>Conference name:</td>
				<td><input type='text' name='conf_name' value=${model.name} > <FONT color="red">${model.nameFieldMissing}</FONT>
				</td>

			</tr>
			<tr>
                <td>Topic:</td>
                <td><input type='text' name='conf_topic' value=${model.topic}> <FONT color="red">${model.topicFieldMissing}</FONT>
                </td>
            </tr>
            <tr>
                <td>Start date:</td>
                <td><input type='text' name='conf_start_date' value=${model.startDate}> <FONT color="red">${model.startDateFieldMissing}</FONT> <FONT color="red">${model.startDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>End date:</td>
                <td><input type='text' name='conf_end_date' value=${model.endDate}> <FONT color="red">${model.endDateFieldMissing}</FONT> <FONT color="red">${model.endDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type='text' name='conf_description' width = 1000 value=${model.description}> <FONT color="red">${model.descriptionFieldMissing}</FONT>
                </td>
            </tr>
            <tr>
                <td>Venue:</td>
                <td><input type='text' name='conf_venue' value=${model.venue}>  <FONT color="red">${model.venueFieldMissing}</FONT>
                </td>
            </tr>

            <tr>
                <td>Max no. of attendees:</td>
                <td><input type='text' name='conf_max_attendees' value=${model.maxAttendees}> <FONT color="red">${model.maxAttendeesFieldMissing}</FONT> <FONT color="red">${model.numberError}</FONT>
                </td>
            </tr>
			<tr>
				<td colspan='2'><input name="save" type="submit"
					value="Save" />
				</td>
			</tr>
            <td>
                <input type="button" value="Cancel"
                       onclick="return confirmCancel()"/>
            </td>
			<tr>
			</tr>
		</table>

	</form>
</body>
</html>
