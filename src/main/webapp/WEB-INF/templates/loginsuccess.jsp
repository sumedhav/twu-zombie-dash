<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        <title>Zombie Dash : Welcome</title>
    </head>
    <body>
        <h1> Zombie Dash <h1>
        <h3> Welcome ${username} !!</h3>
        <table>
            <tr>
                <td style="width:1000px">
                    <table>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/zombie/admin/conference/home">CONFERENCES</a></td>
                        </tr>
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/zombie/admin/users/">USERS</a></td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table>
                        <tr>
                            <td><a href="">LOG OUT</a>
                            </td>
                        </tr>
                        <tr>
                            <td><a href="">CHANGE PASSWORD</a></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </body>
</html>