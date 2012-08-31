<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">
     <head>
            <title>Zombie Dash : Welcome</title>
            <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
            <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
     </head>
          <body>
             <div class="container">
                 <h1 class="pageTitle">
                     Zombie Dash
                 </h1>

                 <h3>
                     Welcome ${username} !!
                     <a name="Logout" id="Logout" href= "${pageContext.request.contextPath}/j_spring_security_logout" style="float:right;" class="btn btn-primary" >Logout</a>
                 </h3>



                 <div>
                    <a name="Conferences" id="Conferences" href="${pageContext.request.contextPath}/zombie/admin/conference/home" class="btn btn-primary">Conferences</a>
                 </div>
                    <br>
                    </br>
                 <div>
                    <a name="Users" id="Users" href="${pageContext.request.contextPath}/zombie/admin/users-management" class="btn btn-primary" > Users </a>
                 </div>
             </div>
          </body>
</html>


