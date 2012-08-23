<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:wrapper>
    <h1>Users</h1>
    <ul>
    <c:forEach var="user" items="${Users}">
    <li><c:out value="${user}"/></li>
    </c:forEach>
    </ul>
    <input type="submit" value="Create User" onclick="location.href='create/'" />
</t:wrapper>
