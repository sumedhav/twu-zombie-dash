<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
<title>Zombie Dash : Create User</title>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/confirm_cancel.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">

</head>
<body >
 <div class="container">
         <h1 class="pageTitle">
             <div>Zombie Dash</div>
         </h1>
         <h3>Create New User</h3>

         <div class="control-group">
            <div name="error_message_div" id="error_message_div" style="color:red">
                ${validationMessage}
                ${allFieldsAreMandatory}
            </div>
         </div>


         <div class="row-fluid">
             <div class="span12">
                 <form id="userDetails" class="form-horizontal" method="post" action="${pageContext.request.contextPath}/zombie/admin/user/create">

                     <div class="control-group">
                          <label class="control-label align-left" for="username">Username:</label>
                          <div class="controls">
                            <textarea name="userName" id="username" type="text" placeholder="enter username">${model.username}</textarea>
                             <span class="error inline-help" style="color:#FF0000">${invalidUserName}</span>
                          </div>
                     </div>

                    <div class="control-group">
                           <label class="control-label align-left" for="password">Password:</label>
                           <div class="controls">
                             <textarea name="password" id="password" type="text" placeholder="enter password">${model.password}</textarea>
                             <span class="error inline-help" style="color:#FF0000">${invalidPassword}</span>
                           </div>
                    </div>

                    <div class="control-group">
                            <label class="control-label align-left" for="name">Name:</label>
                            <div class="controls">
                              <textarea name="name" id="name" type="text" placeholder="enter name">${model.name}</textarea>
                              <span class="error inline-help" style="color:#FF0000">${invalidName}</span>
                            </div>
                    </div>


                     <div class="control-group">
                         <label class="control-label align-left" for="email">Email:</label>
                         <div class="controls">
                            <textarea name="email" id="email" type="text" placeholder="enter email">${model.email}</textarea>
                            <span class="error inline-help" style="color:#FF0000">${invalidEmail}</span>
                         </div>
                     </div>

                     <div class="control-group">
                        <label class="control-label align-left" for="role">Role:</label>
                        <div class="controls">
                        <select name="role" id="role">
                            <option value="GameDesigner">Game Designer</option>
                        </select>
                        </div>
                     </div>

                     <div class="control-group">
                        <div class="controls offset6" >
                             <input id="submit" name="submit" type="submit" value="Save"  class="btn btn-primary" />&nbsp &nbsp
                            <input id="cancel" name="cancel" type="button" value="Cancel" onClick="return confirmCancel('${pageContext.request.contextPath}')" class="btn btn-primary"/>
                        </div>
                     </div>
                 </form>
             </div>
         </div>
     </div>
</form>
</body>
</html>