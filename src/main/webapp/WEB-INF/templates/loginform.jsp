<html>
<head>
    <title>Zombie Dash : Login</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/loginPage.css" />
</head>
<body onload='document.loginForm.Username.focus();'>
    <h1>
        <center>Login</center>
    </h1>
    <form name="loginForm" action="${pageContext.request.contextPath}/zombie/login/Authenticate" method = "POST">

        <div id="login_page">

            <div id="Zombie_img">
                <img src="${pageContext.request.contextPath}/static/images/ZombieDash.jpg"/>
            </div>

            <div id="login_form">

                <div id="message_to_be_displayed">
                    ${messageToBeDisplayed}
                </div>

                <div>
                    <label for="Username">Username:</label>
                </div>

                <div>
                    <input type="text" name="Username" id="Username" />
                </div>

                <div>
                    <label for="Password">Password: </label>
                </div>

                <div>
                    <input type="password" name="Password" id="Password"/>
                </div>

                <div>
                    <input type="submit" value="Login" name="Submit" id="Submit"/>
                </div>

            </div>

        </div>

    </form>
</body>
</html>