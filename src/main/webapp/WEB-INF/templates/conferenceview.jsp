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
          	<div class="row-fluid">
                  <table class="table table-bordered">
                        <tr>
                            <th>Conference Name:</th>
                            <td><c:out value="${Conference.name}"/></td>
                        </tr>
                        <tr>
                            <th class = "topic">Topic:</th>
                            <td><c:out value="${Conference.topic}"/></td>
                        </tr>
                        <tr>
                            <th>Conference start date:</th>
                            <td><c:out value="${Conference.startDate}"/></td>
                        </tr>
                        <tr>
                            <th>Conference end date:</th>
                            <td><c:out value="${Conference.endDate}"/></td>
                        </tr>
                        <tr>
                            <th>Description:</th>
                            <td><c:out value="${Conference.description}"/></td>
                        </tr>
                        <tr>
                            <th>Venue details:</th>
                            <td><c:out value="${Conference.venue}"/></td>
                        </tr>
                        <tr>
                            <th>Max no. of attendees</th>
                            <td><c:out value="${Conference.maxAttendee}"/></td>
                        </tr>
                    </table>

                    </div>

                    <div>
                      <a href="${pageContext.request.contextPath}/zombie/gamedesigner/conference/${Conference.id}/create/task"
                                            name="create_task" id="create_task_${confStatus.count}" class="btn btn-primary offset10">Create Task</a>
                    <br />
                    <br />
                    <a href="${pageContext.request.contextPath}/zombie/admin/conference/list" name="back_conference_view" id="back_conference_view" class="btn btn-primary offset10">Back</a>
                    </div>
                    <div>

                    </div>

 </div>
</body>
</html>

