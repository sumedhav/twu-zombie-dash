<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<t:wrapper>
    <div style="color:RED">
        <p align="center">Oops!! Something went wrong. Your last request could not be completed.</p>
        <p align="center">${errorMessage}</p>
    </div>
    <div>
        <p align="center"><a href="${pageContext.request.contextPath}${urlToReturnTo}">${returnToPrevPageMessage}</a></p>
    </div>

</t:wrapper>