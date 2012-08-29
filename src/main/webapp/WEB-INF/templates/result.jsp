<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <title>
    Results Page
    </title>
    <body>
    <h3>Test Completed</h3>
        Your score is <b id="obtainedScore">${obtainedScore}</b> out of <b id="maxScore">${maxScore}</b>.
    </body>
</html>