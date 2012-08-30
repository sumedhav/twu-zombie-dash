<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

<head>
<title>Zombie Dash : User Details</title>
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/delete_user.js"></script>
</head>
<body>

<div class="container">
     <h1 class="pageTitle">
         <div>Zombie Dash</div>
     </h1>
    <div class="conferenceViewSectionTitle">User Information</div>
          <table class="table table-bordered">
                <tr>
                    <th name="username" id="username">Username:</th>
                    <td name="username_value" id="username_value">${User.userName}</td>
                </tr>
                <tr>
                    <th name="name" id="name">Name:</th>
                    <td  name="name_value" id="name_value">${User.name}</td>
                </tr>
                <tr>
                    <th name="role" id="role">Role:</th>
                    <td  name="role_value" id="role_value">${User.role}</td>
                </tr>
                <tr>
                    <th  name="email" id="email">Email:</th>
                    <td  name="email_value" id="email_value">${User.email}</td>
                </tr>
            </table>

            <div  class="controls offset10" name="edit_delete_back_buttons" id="edit_delete_back_buttons" >
                <a  class="btn btn-primary" name="back_user_details" id="back_user_details"
                     href="${pageContext.request.contextPath}/zombie/admin/users-management">Back</a>
                    <br>
                    </br>
                     <input name="delete_user" id="delete_user" type="button" value="Delete User"
                     onClick="return deleteUser('${pageContext.request.contextPath}','${User.userName}')"  class="btn btn-primary"/>

            </div>
    </div>
    </body>
</html>
