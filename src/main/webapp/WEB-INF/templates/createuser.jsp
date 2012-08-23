<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:wrapper>
    <h1>User Details</h1>
    <form method="post" action="${pageContext.request.contextPath}/zombie/admin/users/create/submit/">
    <p>Username: <input name="username" type="text"  /> </p>
    <p>Password: <input name="password" type="text"  /> </p>
    <p>Name: <input name="name" type="text"  /> </p>
    <p>Role:
    <select name="role">
    <option value="GameDesigner">Game Designer</option>
    </select>
    </p>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" onClick="javascript: history.go(-1)" />
    </form>
</t:wrapper>