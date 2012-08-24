<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta http-equiv="REFRESH" content="1;url=${pageContext.request.contextPath}/zombie/admin/conference/home">
<html>
  <body>
     <h1>
        <center>CONFERENCE INFORMATION</center>
     </h1>
         <form commandName="conferenceForm" action="${pageContext.request.contextPath}/zombie/admin/conference/createConference" method = "GET">
      <input type="submit" value="CLICK TO CREATE CONFERENCE" />


      <t:wrapper>
          <h1>Conferences</h1>
          <ul>
          <c:forEach var="conference" items="${Conferences}">
          <a href="${pageContext.request.contextPath}/zombie/admin/conference/view/${conference}">${conference}</a>
          </c:forEach>
          </ul>
      </t:wrapper>

 <a href="${pageContext.request.contextPath}/zombie/login/HomePage">Back</a>

  </body>
</html>