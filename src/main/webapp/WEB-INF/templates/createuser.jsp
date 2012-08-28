<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <h1>User Details</h1>
    <div id="errorMessageDiv" style="color:red">
    ${validationMessage}
    </div>
    <form id="userDetails" method="post" action="${pageContext.request.contextPath}/zombie/admin/users/create">
    <p>Username: <input name="username" type="text" value="${model.username}"  /> </p>
    <p>Password: <input name="password" type="text" value="${model.password}" /> </p>
    <p>Name: <input name="name" type="text"  value="${model.name}"/> </p>
    <p>Email: <input name="email" type="text" value="${model.email}"/> </p>
    <p>Role:
    <select name="role">
    <option value="GameDesigner">Game Designer</option>
    </select>
    </p>
    <input type="submit" value="Save" />
    <input type="button" value="Cancel" onClick="javascript: history.go(-1)" />
    </form>
</t:wrapper>