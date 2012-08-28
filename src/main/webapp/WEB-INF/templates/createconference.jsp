<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Conference Page</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/create_conference.js"></script>

</head>
<body onload='document.conferenceCreationForm.conf_name.focus();'>
	<h3>Create Conference: Enter Details</h3>
	 <h4> <FONT color="red">${model.errorString}</FONT></h4>

	<form name='conferenceCreationForm' action="${pageContext.request.contextPath}/zombie/admin/conference/submit" method='POST'>

		<table>
			<tr>
				<td>Conference name:</td>
				<td>

                    <textarea name='conf_name' onkeyup="limitNumOfCharsInField(conf_name,100,'#nameExceedError');"
                              rows="1" cols="20">${model.name}</textarea>
                    <label id="nameExceedError" style="color:#FF0000"></label>
                    <FONT color="red">${model.nameFieldMissing}</FONT>
				</td>

			</tr>
			<tr>
                <td>Topic:</td>
                <td><textarea name='conf_topic' onkeyup="limitNumOfCharsInField(conf_topic,100,'#topicExceedError');"
                              rows="1" cols="20">${model.topic}</textarea>
                    <label id="topicExceedError" style="color:#FF0000"></label>
                    <FONT color="red">${model.topicFieldMissing}</FONT>
                </td>
            </tr>
            <tr>
                <td>Start date (yyyy-mm-dd):</td>
                <td><input type='text' name='conf_start_date' size="28" value=${model.startDate}> <FONT color="red">${model.startDateFieldMissing}</FONT> <FONT color="red">${model.startDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>End date (yyyy-mm-dd):</td>
                <td><input type='text' name='conf_end_date' size="28" value=${model.endDate}> <FONT color="red">${model.endDateFieldMissing}</FONT> <FONT color="red">${model.endDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name='conf_description' onkeyup="limitNumOfCharsInField(conf_description,500,'#descriptionExceedError');"
                              rows="1" cols="20">${model.description}</textarea>
                    <label id="descriptionExceedError" style="color:#FF0000"></label>
                    <FONT color="red">${model.descriptionFieldMissing}</FONT>
                </td>
            </tr>

            <tr>
                <td>Venue:</td>
                <td><textarea name='conf_venue' onkeyup="limitNumOfCharsInField(conf_venue,200,'#venueExceedError');"
                              rows="1" cols="20">${model.venue}</textarea>
                    <label id="venueExceedError" style="color:#FF0000"></label>
                    <FONT color="red">${model.venueFieldMissing}</FONT>
                </td>
            </tr>

            <tr>
                <td>Max no. of attendees:</td>
                <td><input type='text' name='conf_max_attendees' size="28" value=${model.maxAttendees}> <FONT color="red">${model.maxAttendeesFieldMissing}</FONT> <FONT color="red">${model.numberError}</FONT>
                </td>
            </tr>
			<tr>
				<td colspan='2'><input name="save" type="submit"
					value="Save" />
				</td>
            </tr>
            <td>
                <input type="button" value="Cancel"
                       onclick="return confirmCancel('${pageContext.request.contextPath}')"/>
            </td>
            <tr>
			</tr>
		</table>

	</form>

</body>
</html>
