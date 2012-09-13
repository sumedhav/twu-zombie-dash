<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
    <jsp:attribute name="title">
        Zombie Dash : Game Designer Home
    </jsp:attribute>
    <jsp:body>
         <div class="container">
             <h1 class="pageTitle">
                 <div>Welcome ${username}</div>
             </h1>

              <div name="existing_conferences" id="existing_conferences" class="conferenceViewSectionTitle">
                Existing Conferences
              </div>

              <div class="conferenceList">
                  <ol>
                      <c:forEach var="conference" items="${Conferences}" varStatus="confStatus">
                          <li>
                                  <a href="${pageContext.request.contextPath}/zombie/gamedesigner/conference/${conference.id}"
                                  name="existing_conference_id" id="existing_conference_id_${confStatus.count}"><c:out value="${conference.name}"/></a>
                          </li>
                          <br>
                          </br>
                      </c:forEach>
                      <div id="empty_conference_message">${emptyConferenceListMessage}</div>
                  </ol>
              </div>
         <a name="Logout" id="Logout" href= "${pageContext.request.contextPath}/j_spring_security_logout" style="float:right;" class="btn btn-primary" >Logout</a>

         </div>
    </jsp:body>
</t:wrapper>