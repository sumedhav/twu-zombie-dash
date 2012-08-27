<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:wrapper>
    <h1>Users</h1>
    <table>
    <tr>
        <td>Name</td>
        <td>Role</td>
    </tr>
    <c:forEach var="user" items="${Users}">
    <tr>
        <td><a href="${pageContext.request.contextPath}/zombie/admin/users/display/${user.userName}"><c:out value="${user.name}"/></a></td>
        <td><c:out value="${user.role}"/></td>
    </tr>
    </c:forEach>
    </table>
    <input type="submit" value="Create User" onclick="location.href='${pageContext.request.contextPath}/zombie/admin/users/create'" />
</t:wrapper>
