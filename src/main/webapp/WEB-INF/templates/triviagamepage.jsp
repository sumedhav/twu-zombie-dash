<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
  <title>Welcome to Trivia Game!</title>
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
    </script>
</head>
<body class="home">
  <h2>Welcome YAHYA to JAVA CONFERENCE</h2>
  <form method="post" action="/zombie/conference/user/game/result" onSubmit="return validate(${fn:length(questions)})">
    <c:forEach var="question" items="${questions}" varStatus="questionStatus">
    <h3>${question.text}</h3>
      <table>
      <tr>
        <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
          <td>
            <input name="question_${questionStatus.count}" value="${option.text}" id="option_${questionStatus.count}_${optionStatus.count}" type="radio" />
            <label for="option_${questionStatus.count}_${optionStatus.count}">${option.text}</label>
          </td>
          <c:if test = '${(optionStatus.count) % 2 == 0}'></tr><tr>
          </c:if>
        </c:forEach>
        </tr>
      </table>
    </c:forEach>
    <div>
      <input type="submit" value="SUBMIT" />
    </div>
  </form>
</body>
</html>
