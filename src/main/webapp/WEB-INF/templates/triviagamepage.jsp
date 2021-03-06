<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/trivia_game_logic.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/confirm_cancel.js"></script>
<script type="text/javascript">
 if(!isMobileDevice()){
         document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">')
         document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">')

         }
</script>
<title>Welcome to Trivia Game!</title>
<meta name="viewport" content="width=device-width">
</head>
<body>
 <div class="container">
         <h1 class="pageTitle">
             <c:out value="${task.name}"/>
         </h1>
              <input type="image" id="link"
             onclick="return doExpandCollapse('box','link','${pageContext.request.contextPath}')"
             src="${pageContext.request.contextPath}/static/images/sideArrow.jpg">Description

        <div id="box" name=tbl style="overflow:hidden;display:none;width: 80%; height: 10%; border: 3px solid gray;">
                   <label id="description"><c:out value="${task.description}"/></label><br>
        </div>
        <div class="row-fluid">
             <div class="span12">
                 <form  class="form-horizontal" method="post" action="${pageContext.request.contextPath}/zombie/attendee/task/${incompleteTaskId}"
                    onSubmit="return validate(${fn:length(questions)},'#incompleteQuestionsError')">
                     <div name="error_message_div" class="error" id="incompleteQuestionsError"></div>
                  <c:forEach var="question" items="${questions}" varStatus="questionStatus">
                     <div class="question"><c:out value="${question.text}"/></div>
                         <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
                             <input class="options" name="${question.questionId}" value="${option.optionId}" id="option_${questionStatus.count}_${optionStatus.count}" type="radio" >&nbsp
                              <c:out value="${option.text}"/></input>
                             <br>
                           <c:if test = '${(optionStatus.count) % 2 == 0}'></tr><tr>
                           </c:if>
                         </c:forEach>
                  </c:forEach>

                   <br/>
                   <br/>

                   <div class="control-group">
                    <div class="controls offset8" >
                         <input type="submit" value="Submit" name="submit" id="submit_button"  class="btn btn-primary"/>&nbsp &nbsp
                         <input type="button" value="Cancel" onclick="return confirmCancel('${pageContext.request.contextPath}','/zombie/attendee/home')" name="cancel" id="cancel_button" class="btn btn-primary"/>
                    </div>
                   </div>
                 </form>
             </div>
         </div>
 </div>
</body>
</html>
