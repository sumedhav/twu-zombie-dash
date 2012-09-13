<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:mobile>
    <jsp:attribute name="title">
        Error Page
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1><center>Oops!!</center></h1>
    </jsp:attribute>

    <jsp:body>
    <div style="color:RED">
        <p align="center">${taskAlreadyComplete}</p>
    </div>
    <div>
    <div>
        <p align="center"><a href="${pageContext.request.contextPath}${urlToReturnTo}">${returnToPrevPageMessage}</a></p>
    </div>
    </jsp:body>
</t:mobile>