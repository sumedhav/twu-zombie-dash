<html>
<head>
    <title>Zombie Dash : Login</title>
</head>
<body onload='document.loginForm.Username.focus();'>
    <h1>
        <center>Login</center>
    </h1>
    <form name="loginForm" action="${pageContext.request.contextPath}/zombie/login/Authenticate" method = "POST">
        <div style="color:red; text-align:center">
        ${messageToBeDisplayed}
        </div>
        <table align="center">
            <tr>
                <td>
                    <p>
                        <img align="right" src="${pageContext.request.contextPath}/static/images/ZombieDash.jpg"/>
                    </p>
                </td>
                <td>
                    <table style="margin-left:50">
                        <tr>
                            <td>Username:<FONT color="red"><form:errors
                            path="username" /></FONT></td>
                        </tr>
                        <tr>
                            <td><input path="username" type = "text" name = "Username" /></td>
                        </tr>
                        <tr>
                            <td>Password:<FONT color="red"><form:errors
                            path="password" /></FONT></td>
                        </tr>
                        <tr>
                            <td><input path="password" type ="password" name = "Password" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Submit" name = "Submit"/></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>