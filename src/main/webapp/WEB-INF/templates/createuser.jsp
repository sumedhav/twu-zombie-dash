<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Zombie Dash : Create User</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/confirm_cancel.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">

</head>
<body onload='document.userDetails.userName.focus();'>
 <div class="container">
         <h1 class="pageTitle">
             <div>Zombie Dash</div>
         </h1>
         <h3>Create New User</h3>

         <div class="control-group">
            <div class="form-message">
                <b>All (<span class="error inline-help">*</span>) fields are mandatory.</b>                  ${model.errorString}
            </div>
            <div name="error_message_div" id="error_message_div" style="color:red">
                <b>
                ${validationMessage}
                </b>
            </div>
         </div>


         <div class="row-fluid">
             <div class="span12">
                 <form id="userDetails" class="form-horizontal" name="userDetails" method="post" action="${pageContext.request.contextPath}/zombie/admin/user/create">

                     <div class="control-group">
                          <label class="control-label align-left" for="username"><span class="error inline-help">*</span>Username:</label>
                          <div class="controls">
                            <input type="text" name="userName" id="username" value="<c:out value="${model.username}"/>" placeholder="enter username"/>
                            <span id="username_field_empty" class="error inline-help">${usernameFieldEmpty}</span>
                            <div id="invalid_user_name" class="error inline-help">${invalidUserName}</div>
                          </div>
                     </div>

                    <div class="control-group">
                           <label class="control-label align-left" for="password"><span class="error inline-help">*</span>Password:</label>
                           <div class="controls">
                             <input type="text" name="password" id="password" value="<c:out value="${model.password}"/>" placeholder="enter password"/>
                             <span id="password_field_empty" class="error inline-help">${passwordFieldEmpty}</span>
                             <div id="invalid_password" class="error inline-help">${invalidPassword}</div>
                           </div>
                    </div>

                    <div class="control-group">
                            <label class="control-label align-left" for="name"><span class="error inline-help">*</span>Name:</label>
                            <div class="controls">
                              <input type="text" name="name" id="name" value="<c:out value="${model.name}"/>" placeholder="enter name"/>
                              <span id="name_field_empty" class="error inline-help">${nameFieldEmpty}</span>
                              <div id="invalid_name" class="error inline-help">${invalidName}</div>
                            </div>
                    </div>


                     <div class="control-group">
                         <label class="control-label align-left" for="email"><span class="error inline-help">*</span>Email:</label>
                         <div class="controls">
                            <input type="text" name="email" id="email" value="<c:out value="${model.email}"/>" placeholder="enter email"/>
                            <span id="email_field_empty" class="error inline-help">${emailFieldEmpty}</span>
                            <div id="invalid_email" class="error inline-help">${invalidEmail}</div>
                         </div>
                     </div>

                     <div class="control-group">
                        <label class="control-label align-left" for="role">&#160;Role:</label>
                        <div class="controls">
                        <select name="role" id="role">
                            <option value="GameDesigner">Game Designer</option>
                        </select>
                        </div>
                     </div>

                     <div class="control-group">
                        <div class="controls offset6" >
                             <input id="submit" name="submit" type="submit" value="Save"  class="btn btn-primary" />&nbsp &nbsp
                            <input id="cancel" name="cancel" type="button" value="Cancel" onClick="return confirmCancel('${pageContext.request.contextPath}','/zombie/admin/users/list')" class="btn btn-primary"/>
                        </div>
                     </div>
                 </form>
             </div>
         </div>
     </div>
</form>
</body>
</html>