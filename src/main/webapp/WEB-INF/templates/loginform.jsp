
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Zombie Dash : Login</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
</head>
<body onload='document.loginForm.Username.focus();'>
    <div class="container">
        <h1 class="pageTitle">
            <div>Login</div>
        </h1>
        <div class="control-group">
            <div id="message_to_be_displayed" class="form-message error">
                ${messageToBeDisplayed}
            </div>
        </div>
        <div class="span6">
            <img src="${pageContext.request.contextPath}/static/images/ZombieDash.jpg"/>
        </div>
        <div class="row-fluid">
            <div class="span6">
                <form id="loginForm" class="form-horizontal" name="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check" method = "POST">
                    <div class="control-group">
                        <label class="control-label" for="Username">Username:</label>
                        <div class="controls">
                            <input type="text" name="j_username" id="username" placeholder="enter username"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="Password">Password: </label>
                        <div class="controls">
                            <input type="password" name="j_password" id="password" placeholder="enter password"/>
                        </div>
                    </div>

                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btn-primary">Login</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>