<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Zombie Dash : Create User</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/createUser.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/confirm_cancel.js"></script>
</head>
<body>
    <h1>Zombie Dash</h1>
    <h3>Users : Create New</h3>

    <div name="error_message_div" id="error_message_div" style="color:red">
        ${validationMessage}
        ${allFieldsAreMandatory}
    </div>
    <form id="userDetails" method="post" action="${pageContext.request.contextPath}/zombie/admin/user/create">

        <div>
            <label for="username">Username:</label>
            <input name="userName" id="username" type="text" value="${model.username}"/>
            <FONT color="red" id="invalid_user_name">${invalidUserName}</FONT>
        </div>

        <div>
            <label for="password">Password: </label>
            <input name="password" id="password" type="text" value="${model.password}" />
            <FONT color="red" id="invalid_password">${invalidPassword}</FONT>
        </div>

        <div>
            <label for="name">Name: </label>
            <input name="name" id="name" type="text"  value="${model.name}"/>
            <FONT color="red" id="invalid_name">${invalidName}</FONT>
        </div>

        <div>
            <label for="email">Email: </label>
            <input name="email" id="email" type="text" value="${model.email}"/>
            <FONT color="red" id="invalid_email">${invalidEmail}</FONT>
        </div>

        <div>
            <label for="role">Role:</label>
            <select name="role" id="role">
                <option value="GameDesigner">Game Designer</option>
            </select>
        </div>

        <input id="cancel" name="cancel" type="button" value="Cancel" onClick="return confirmCancel('${pageContext.request.contextPath}')" />
        <input id="submit" name="submit" type="submit" value="Save" />
    </form>
</body>
</html>