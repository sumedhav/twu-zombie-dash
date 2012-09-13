<%@tag description="Form wrapper" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@attribute name="title" fragment="true" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="script" fragment="true" %>
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title> <jsp:invoke fragment="title"/> </title>
      <script type="text/javascript">
       if(!((navigator.userAgent.match(/iPhone/i)) ||
                      (navigator.userAgent.match(/iPod/i)) ||
                      (navigator.userAgent.match(/Android/i)) ) || (navigator.userAgent.match(/iPod/i))){
                      document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">')
                      document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">')
                      document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/css/custom.datepick.css" rel="stylesheet"/>')
                      }
      </script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-1.8.1.js"></script>
    <script type="text/javascript"><jsp:invoke fragment="script"/></script>
  </head>
  <body>
      <div id="pageheader">
        <jsp:invoke fragment="header"/>
      </div>
    <jsp:doBody/>
  </body>
</html>
