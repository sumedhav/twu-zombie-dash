<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <div style="color:RED">
    <p align="center">Oops!! Something went wrong. Your last request could not be completed.</p>
    </div>
    <div>
    <p align="center"><a href="${pageContext.request.contextPath}/zombie/admin/users/">Go Back To Users Page</a></p>
    </div>

</t:wrapper>