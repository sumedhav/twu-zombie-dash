<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Results Page</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
</head>

    <body>
         <div class="container">
            <h1 class="pageTitle">
                <div>Test Completed</div>
            </h1>
            <div class="sectionTitle">
                Your score is <b id="obtainedScore">${obtainedScore}</b> out of <b id="maxScore">${maxScore}</b>.
            </div>
         </div>
    </body>
</html>