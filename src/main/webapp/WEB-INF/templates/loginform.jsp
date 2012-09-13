<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:mobile>
   <jsp:attribute name="title">
        Zombie Dash : Login
    </jsp:attribute>
    <jsp:attribute name="script">
        $(document).ready(function() {
          document.loginForm.j_username.focus();
        });
    </jsp:attribute>
<jsp:body>

    <div class="container">
        <h1 class="pageTitle">
            <div>Login</div>
        </h1>
        <div class="control-group">
            <div id="message_to_be_displayed" class="authentication-message error">
                ${messageToBeDisplayed}
                ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
            <div id="logout_message" class="authentication-message information">
                ${logoutMessage}
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
</jsp:body>
</t:mobile>