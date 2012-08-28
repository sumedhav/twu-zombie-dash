<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Zombie Dash : User Details</title>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/userView.css" rel="stylesheet">
    </head>

    <body>
        <h1>Zombie Dash</h1>
        <h3>Users : Details</h3>
        <table>
        <tr>
                <td name="username" id="username">Username:</td>
                <td name="username_value" id="username_value">${User.userName}</td>
        </tr>
        <tr>
                <td name="name" id="name">Name:</td>
                <td name="name_value" id="name_value">${User.name}</td>
        </tr>
        <tr>
                <td name="role" id="role">Role:</td>
                <td name="role_value" id="role_value">${User.role}</td>
        </tr>
        <tr>
                <td name="email" id="email">Email:</td>
                <td name="email_value" id="email_value">${User.email}</td>
        </tr>
        </table>
        <div name="edit_delete_back_buttons" id="edit_delete_back_buttons">
            <a name="edit_details" id="edit_details" href="" value="Edit Details">Edit Details</a>
            <a name="delete_user" id="delete_user" href="${pageContext.request.contextPath}/zombie/admin/users/deleteuser/${User.userName}">Delete User</a>
            <a name="back_user_details" id="back_user_details" href="${pageContext.request.contextPath}/zombie/admin/users">Back</a>
        </div>
    </body>
</html>
