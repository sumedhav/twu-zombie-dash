<%@ taglib prefix="hnw" tagdir="/WEB-INF/tags/hnw" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <title>Zombie Dash : Registration Confirmed</title>
    <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="pageTitle">
            <div>Registration Confirmed</div>
        </h1>
        <div class="form-message">
          Thank you for registering at Zombie Dash, <c:out value="${registeredName}"/>.<br/><br/>Please
          <a name="Login" id="Login" href= "${pageContext.request.contextPath}/zombie/login">login.</a>
        </div>
    </div>

</body>
</html>