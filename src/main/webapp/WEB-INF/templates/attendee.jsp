<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Attendee Home</title>
    <script type="text/javascript">
     if(!((navigator.userAgent.match(/iPhone/i)) ||
             (navigator.userAgent.match(/iPod/i)) ||
             (navigator.userAgent.match(/Android/i)) ) || (navigator.userAgent.match(/iPod/i))){
             document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">')
             document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">')
             }
    </script>
      <meta name="viewport" content="width=device-width">

</head>

<body>
     <div class="container">
        <h1 class="pageTitle">
            <div>Welcome Attendee!!!</div>
        </h1>
         <div class="sectionTitle">
         Your current score is <b id="obtainedScore">${obtainedScore}</b>
         </div>
         </br>
         <div class="sectionTitle">
            Tasks
         </div>
         <ol>
            <c:forEach var="incompleteTask" items="${incompleteTasks}" varStatus="taskStatus">
              <li>
                <a href="${pageContext.request.contextPath}/zombie/attendee/task/${incompleteTask.id}">${incompleteTask.name}</a>
              </li>
            </c:forEach>
         </ol>
     </div>
</body>
</html>