<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
     <head>
            <title>Zombie Dash : Welcome</title>
            <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
            <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
     </head>
          <body>
             <div class="container">
                 <h1 class="pageTitle">
                     <div>Zombie Dash</div>
                 </h1>
                 <h3>
                     Welcome ${username} !!
                 </h3>
                    <a name="Logout" id="Logout" href= "${pageContext.request.contextPath}/zombie/login/Logout" class="btn btn-primary offset10" >Logout</a>

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


