<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Conference Page</title>

</head>
<body onload='document.conferenceCreationForm.conf_name.focus();'>
	<h3>Create Conference: Enter Details</h3>

	<form name='conferenceCreationForm' action="/zombie/admin/conference/submit"
		method='POST'>

		<table>
			<tr>
				<td>Conference Name:</td>
				<td><input type='text' name='conf_name' value=''>
				</td>
			</tr>
			<tr>
                <td>Topic:</td>
                <td><input type='text' name='conf_topic' />
                </td>
            </tr>
            <tr>
                <td>Start Date:</td>
                <td><input type='text' name='conf_start_date' />
                </td>
            </tr>
            <tr>
                <td>End Date:</td>
                <td><input type='text' name='conf_end_date' />
                </td>
            </tr>

            <tr>
                <td>Description:</td>
                <td><input type='text' name='conf_description' width = 1000/>
                </td>
            </tr>
            <tr>
                <td>Venue:</td>
                <td><input type='text' name='conf_venue' />
                </td>
            </tr>
            <tr>
                <td>Speaker Name:</td>
                <td><input type='text' name='conf_speaker_name' />
                </td>
            </tr>
            <tr>
                <td>Speaker Domain:</td>
                <td><input type='text' name='conf_speaker_domain' />
                </td>
            </tr>
            <tr>
                <td>Speaker Contact No:</td>
                <td><input type='text' name='conf_speaker_contact_number' />
                </td>
            </tr>
            <tr>
                <td>Speaker Email Address:</td>
                <td><input type='text' name='conf_speaker_email' />
                </td>
            </tr>
            <tr>
                <td>Organiser Name:</td>
                <td><input type='text' name='conf_organiser_name' />
                </td>
            </tr>
            <tr>
                <td>Organiser Contact No:</td>
                <td><input type='text' name='conf_organiser_contact_number' />
                </td>
            </tr>
            <tr>
                <td>Organiser Email Address:</td>
                <td><input type='text' name='conf_organiser_email' />
                </td>
            </tr>
            <tr>
                <td>Max Number Of Attendees:</td>
                <td><input type='text' name='conf_max_attendees' />
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