<html>
<body>
    <h1>
        <center> Login Form </center>
    </h1>
    <form commandName="loginForm" action="${pageContext.request.contextPath}/zombie/login/Authenticate" method = "POST">
        <div style="color:red; text-align:center">
        ${errorMessage}
        </div>
        <table>
            <tr>
                <td>
                    <p>
                        <img align="right" src="${pageContext.request.contextPath}/static/images/Zombie-Dash.jpg"/>
                    </p>
                </td>
                <td>
                    <table>
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
                            <td><input type="submit" value="Submit" /></td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>