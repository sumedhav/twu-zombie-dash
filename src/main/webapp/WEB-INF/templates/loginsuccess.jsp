<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <title>Zombie Dash : Welcome</title>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/homePage.css" />
    </head>
    <body>
        <h1> Zombie Dash <h1>
        <h3> Welcome ${username} !!</h3>
        <div>
            <a name="Logout" id="Logout" href= "${pageContext.request.contextPath}/j_spring_security_logout" >Logout</a>
        </div>
        <div>
            <a name="Conferences" id="Conferences" href="${pageContext.request.contextPath}/zombie/admin/conference/home">Conferences</a>
        </div>
        <div>
            <a name="Users" id="Users" href="${pageContext.request.contextPath}/zombie/admin/users/" > Users </a>
        </div>

    </body>
</html>