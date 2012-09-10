<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Zombie Dash : Conference Home</title>
        <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
    </head>
      <body>
         <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>

             <div class="sectionTitle">
                <a href="${pageContext.request.contextPath}/zombie/admin/conference/create" name="conference_creation"
                    id="conference_creation" class="btn btn-primary">Create New Conference
                </a>
             </div>

              <div name="existing_conferences" id="existing_conferences" class="sectionTitle">
                Existing Conferences
              </div>

              <div class="conferenceList">
                  <ol>
                      <c:forEach var="conference" items="${Conferences}" varStatus="confStatus">
                          <li>
                                  <a href="${pageContext.request.contextPath}/zombie/admin/conference/view/${conference.id}"
                                  name="existing_conference_id" id="existing_conference_id_${confStatus.count}"><c:out value="${conference.name}"/></a>
                          </li>
                          <br>
                          </br>
                      </c:forEach>
                      <div id="empty_conference_message">${emptyConferenceListMessage}</div>
                  </ol>
              </div>

              <div>
                  <a href="${pageContext.request.contextPath}/zombie/admin/home"
                  name="back_conference_home" id="back_conference_home" class="btn btn-primary offset8" >Back</a>
              </div>
         </div>
    </body>
</html>
