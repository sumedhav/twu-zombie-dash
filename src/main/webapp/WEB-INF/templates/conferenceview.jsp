<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Zombie Dash : Conference Information</title>
   <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
</head>
<body>

 <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>
          	<div class="conferenceViewSectionTitle">Conference Information</div>
                  <table class="table table-bordered">
                        <tr>
                            <th>Conference Name:</th>
                            <td>${Conference.name}</td>
                        </tr>
                        <tr>
                            <th class = "topic">Topic:</th>
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

                    </div>
                    <div class="controls offset12">
                    <a href="${pageContext.request.contextPath}/zombie/admin/conference/home" name="back_conference_view" id="back_conference_view" class="btn btn-primary">Back</a>
                    </div>
            </body>
 </html>

