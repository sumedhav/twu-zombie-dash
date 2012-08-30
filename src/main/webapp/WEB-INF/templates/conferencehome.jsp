<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Zombie Dash : Conference Home</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/encodeurl.js"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
    </head>
      <body>
         <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>

             <div class="sectionTitle">
                <a href="${pageContext.request.contextPath}/zombie/admin/conference/createConference" name="conference_creation"
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
                              <%--<a href="${pageContext.request.contextPath}/zombie/admin/conference/view/${conference}"--%>
                              <%--name="existing_conference_name" id="existing_conference_name_${confStatus.count}">${conference} </a>--%>
                                  <a href="javascript:encodeUrl('${pageContext.request.contextPath}','/zombie/admin/conference/view/','${conference}')"
                                     name="existing_conference_name" id="existing_conference_name_${confStatus.count}">${conference}</a>
                          </li>
                          <br>
                          </br>
                      </c:forEach>
                  </ol>
              </div>

              <div>
                  <a href="${pageContext.request.contextPath}/zombie/login/HomePage"
                  name="back_conference_home" id="back_conference_home" class="btn btn-primary offset8" >Back</a>
              </div>
         </div>
    </body>
</html>
