<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Conference Page</title>

</head>
<body onload='document.conferenceCreationForm.conf_name.focus();'>
	<h3>Create Conference: Enter Details</h3>
	 <h4> <FONT color="red">${model.errorString}</FONT></h4>

	<form name='conferenceCreationForm' action="${pageContext.request.contextPath}/zombie/admin/conference/submit" method='POST'>

		<table>
			<tr>
				<td>Conference Name:</td>
				<td><input type='text' name='conf_name' value=${model.name}>
				</td>
			</tr>
			<tr>
                <td>Topic:</td>
                <td><input type='text' name='conf_topic' value=${model.topic}>
                </td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><input type='text' name='conf_start_date' value=${model.startDate}> <FONT color="red">${model.startDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><input type='text' name='conf_end_date' value=${model.endDate}> <FONT color="red">${model.endDateError}</FONT>
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><input type='text' name='conf_description' width = 1000 value=${model.description}>
                </td>
            </tr>
            <tr>
                <td>Venue:</td>
                <td><input type='text' name='conf_venue' value=${model.venue}>
                </td>
            </tr>

            <tr>
                <td>Max Number Of Attendees:</td>
                <td><input type='text' name='conf_max_attendees' value=${model.maxAttendees}> <FONT color="red">${model.numberError}</FONT>
                </td>
            </tr>
			<tr>
				<td colspan='2'><input name="save" type="submit"
					value="Save" />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="cancel" type="reset"
				    value="Cancel"/>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
