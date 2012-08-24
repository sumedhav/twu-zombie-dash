<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:wrapper>
    <h1>${User.name} Details</h1>
    <table>
    <tr>
        <td>Username:</td>
        <td>${User.userName}</td>
    </tr>
    <tr>
            <td>Password:</td>
            <td>${User.password}</td>
    </tr>
    <tr>
            <td>Name:</td>
            <td>${User.name}</td>
    </tr>
    <tr>
            <td>Role:</td>
            <td>${User.role}</td>
    </tr>
    <tr>
            <td>Email:</td>
            <td>${User.email}</td>
    </tr>
    <tr>
            <td><input type="button" value="Edit Details"/></td>
            <td><input type="button" value="Delete User" onclick="location.href='${pageContext.request.contextPath}/zombie/admin/users/deleteuser/${User.userName}'"/></td>
            <td><input type="button" value="Back" onClick="javascript: history.go(-1)"/></td>
     </tr>
</t:wrapper>
