<html>
<body>

<h3>
<center> Login Form </center>
</h3>

<form commandName="loginForm" action="/zombie/login/Authenticate" method = "POST">

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
</form>
</body>
</html>