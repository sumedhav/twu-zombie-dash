<%@tag description="Basepage wrapper" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<html>
  <head>
    <title> <jsp:invoke fragment="title"/> </title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
  </head>
  <body>
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
  </body>
</html>

