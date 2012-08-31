<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Welcome to Trivia Game!</title>

   <link type="text/css" href="${pageContext.request.contextPath}/static/css/commonPatterns.css" rel="stylesheet">
   <link type="text/css" href="${pageContext.request.contextPath}/static/css/triviaGamePage.css" rel="stylesheet">

    <script>
      function validate(numQns){
        var radios = document.getElementsByTagName('input');
        var value=0;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].type === 'radio' && radios[i].checked) {
                value++;
            }
        }
        if (value < numQns) {
          alert("You need to answer all the questions!");
          return false;
          }
        else {
          return true;
        }
      }

      function confirmCancel() {
        if(confirm("ARE YOU SURE YOU WANT TO LEAVE?")) {
          location.replace("${pageContext.request.contextPath}/zombie/conference/user/home/");
        }
      }

    </script>
</head>
<body class="home">
  <h2>Welcome YAHYA to JAVA CONFERENCE</h2>
  <form method="post" action="${pageContext.request.contextPath}/zombie/conference/user/game/result" onSubmit="return validate(${fn:length(questions)})">
    <c:forEach var="question" items="${questions}" varStatus="questionStatus">
    <div class="question">${question.text}</div>
      <table>
      <tr>
        <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
          <td>
            <input class="options" name="question_${questionStatus.count}" value="${option.text}" id="option_${questionStatus.count}_${optionStatus.count}" type="radio" />
            <label for="option_${questionStatus.count}_${optionStatus.count}">${option.text}</label>
          </td>
          <c:if test = '${(optionStatus.count) % 2 == 0}'></tr><tr>
          </c:if>
        </c:forEach>
        </tr>
      </table>
    </c:forEach>
    <div>
      <input type="submit" value="submit" name="submit" id="submit_button"/>
    </div>
    <div>
      <input type="button" value="cancel" onclick="return confirmCancel()" name="cancel" id="cancel_button"/>
    </div>
  </form>
</body>
</html>
