<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>Zombie Dash : User List</title>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/userList.css" rel="stylesheet">
    </head>
    <body>
        <h1>Zombie Dash</h1>
        <h3>Users</h3>
        <table id="user_list_table" name="user_list_table">
            <tr id="user_list_table_heading" name="user_list_table_heading">
                <th>Name</th>
                <th>Role</th>
            </tr>
            <c:forEach var="user" items="${Users}">
                <tr>
                    <td><a href="${pageContext.request.contextPath}/zombie/admin/users/display/${user.userName}" id="username_value" name="username_value"><c:out value="${user.name}"/></a></td>
                    <td><c:out value="${user.role}"/></td>
                </tr>
            </c:forEach>
        </table>
        <div>
            <a name="create_user" id="create_user" href="${pageContext.request.contextPath}/zombie/admin/users/create">Create New User</a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/zombie/login/HomePage" name="back_user_home" id="back_user_home">Back</a>
        </div>
    </body>
</html>
