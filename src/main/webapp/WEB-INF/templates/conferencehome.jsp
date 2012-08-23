<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <body>
     <h1>
        <center>CONFERENCE INFORMATION<center>
     </h1>
         <form commandName="conferenceForm" action="${pageContext.request.contextPath}/zombie/admin/conference/createConference" method = "GET">
      <input type="submit" value="CLICK TO CREATE CONFERENCE" />


      <t:wrapper>
          <h1>Conferences</h1>
          <ul>
          <c:forEach var="conference" items="${Conferences}">
          <li><c:out value="${conference}"/></li>
          </c:forEach>
          </ul>
      </t:wrapper>

  </body>
</html>