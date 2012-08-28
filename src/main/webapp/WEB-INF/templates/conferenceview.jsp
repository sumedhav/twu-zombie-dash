<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Zombie Dash : Conference Information</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/conferenceView.css" rel="stylesheet">
</head>
<body>
    <h1>Zombie Dash</h1>
    <h3>Conference Information</h3>
    <table class="table" align="center">
        <tr>
            <th>Conference Name:</th>
            <td>${Conference.name}</td>
        </tr>
        <tr>
            <th>Topic:</th>
            <td>${Conference.topic}</td>
        </tr>
        <tr>
            <th>Conference start date:</th>
            <td>${Conference.startDate}</td>
        </tr>
        <tr>
            <th>Conference end date:</th>
            <td>${Conference.endDate}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${Conference.description}</td>
        </tr>
        <tr>
            <th>Venue details:</th>
            <td>${Conference.venue}</td>
        </tr>
        <tr>
            <th>Max no. of attendees</th>
            <td>${Conference.maxAttendee}</td>
        </tr>
    </table>
    <a href="${pageContext.request.contextPath}/zombie/admin/conference/home" name="back_conference_view" id="back_conference_view">Back</a>
</body>

</html>