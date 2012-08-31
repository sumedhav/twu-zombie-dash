<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title>Zombie Dash : User List</title>
   <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
</head>
    <body>

          <div class="container">
              <h1 class="pageTitle">
                  <div>Zombie Dash</div>
              </h1>
              <h3 class= "sectionTitle">
                  <div>Users</div>
              </h3>
              <div class="userList">
                   <table class= "table table-bordered" id="user_list_table" name="user_list_table">
                        <tr id="user_list_table_heading" name="user_list_table_heading">
                            <th>Name</th>
                            <th>Role</th>
                        </tr>
                        <c:forEach var="user" items="${Users}" varStatus = "userStatus">
                        <tr>
                            <td><a href="${pageContext.request.contextPath}/zombie/admin/user/${user.userName}" id="username_value_${userStatus.count}" name="username_value"><c:out value="${user.name}"/></a></td>
                            <td><c:out value="${user.role}"/></td>
                        </tr>
                       </c:forEach>
                   </table>
              </div>
                   <div>
                       <a name="create_user" id="create_user" href="${pageContext.request.contextPath}/zombie/admin/user/create" class="btn btn-primary">Create New User</a>
                   </div>
                   <br>
                   </br>
                   <br>
                   </br>
                   <div>
                       <a href="${pageContext.request.contextPath}/zombie/admin/home" name="back_user_home" id="back_user_home" class="btn btn-primary offset11">Back</a>
                   </div>
          </div>
    </body>
</html>

