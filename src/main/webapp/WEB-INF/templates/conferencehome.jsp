<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <body>
     <h1>
        <center>Conference Information</center>
     </h1>
         <form commandName="conferenceForm" action="${pageContext.request.contextPath}/zombie/admin/conference/createConference" method = "GET">
      <input type="submit" value="CLICK TO CREATE CONFERENCE"/>


      <t:wrapper>
          <h1>Conferences</h1>
          <ul>
              <ol>
                  <c:forEach var="conference" items="${Conferences}">
                      <div>
                          <li>
                          <a href="${pageContext.request.contextPath}/zombie/admin/conference/view/${conference}">${conference}</a>
                          </li>
                      </div>

                  </c:forEach>
              </ol>

          </ul>
      </t:wrapper>

 <a href="${pageContext.request.contextPath}/zombie/login/HomePage">Back</a>

  </body>
</html>