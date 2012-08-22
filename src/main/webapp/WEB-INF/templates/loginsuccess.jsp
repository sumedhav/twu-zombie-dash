<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <body>
        <h3> Welcome <core:out value="${loginForm.username}" /></h3>
        <table>
            <tr>
                <td style="width:1000px;text-align:right;"><a href="${pageContext.request.contextPath}/zombie/login/LoginForm">LOG OUT</a></td>
            </tr>

            <tr>
                <td  style="width:1000px;text-align:right;"><a href="">CHANGE PASSWORD</a></td>
            </tr>
            <tr>
                <td><a href="">CONFERENCES</a></td>
            </tr>
            <tr>
                <td><a href="/zombie/admin/users/">USERS</a></td>
            </tr>

        </table>
    </body>
</html>