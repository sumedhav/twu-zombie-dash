<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Welcome to Trivia Game!</title>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/trivia_game_logic.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">

</head>
<body >
 <div class="container">
         <h1 class="pageTitle">
             <div>Welcome USERNAME to CONFERENCE NAME</div>
         </h1>

         <div class="row-fluid">
             <div class="span12">
                 <form  class="form-horizontal" method="post" action="${pageContext.request.contextPath}/zombie/conference/user/game/result"
                 onSubmit="return validate(${fn:length(questions)})">

                  <c:forEach var="question" items="${questions}" varStatus="questionStatus">
                     <div class="question">${question.text}</div>
                       <table class ="table1">
                       <tr>
                         <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
                           <td>
                             <input class="options" name="question_${questionStatus.count}" value="${option.text}" id="option_${questionStatus.count}_${optionStatus.count}" type="radio" >&nbsp ${option.text}</input>
                           </td>
                           <c:if test = '${(optionStatus.count) % 2 == 0}'></tr><tr>
                           </c:if>
                         </c:forEach>
                         </tr>
                       </table>
                  </c:forEach>

                   <br/>
                   <br/>

                   <div class="control-group">
                    <div class="controls offset8" >
                         <input type="submit" value="Submit" name="submit" id="submit_button"  class="btn btn-primary"/>&nbsp &nbsp
                         <input type="button" value="Cancel" onclick="return confirmCancel('${pageContext.request.contextPath}')" name="cancel" id="cancel_button" class="btn btn-primary"/>
                    </div>
                   </div>
                 </form>
             </div>
         </div>
 </div>
</body>
</html>
