<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Zombie Dash : Conference Home</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/conferenceHome.css"/>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
    </head>
      <body>
          <h1>Zombie Dash</h1>

          <div>
            <a href="${pageContext.request.contextPath}/zombie/admin/conference/createConference" name="conference_creation" id="conference_creation">Create New Conference</a>
          </div>

          <div name="existing_conferences" id="existing_conferences">
            Existing Conferences
          </div>

          <div>
              <ul>
                      <c:forEach var="conference" items="${Conferences}">
                          <div>
                              <a href="${pageContext.request.contextPath}/zombie/admin/conference/view/${conference}" name="existing_conference_name" id="existing_conference_name">${conference}</a>
                          </div>
                      </c:forEach>
              </ul>
          </div>

          <div>
              <a href="${pageContext.request.contextPath}/zombie/login/HomePage" name="back_conference_home" id="back_conference_home">Back</a>
          </div>
    </body>
</html>