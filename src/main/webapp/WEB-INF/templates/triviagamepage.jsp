<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Welcome to Trivia Game!</title>
</head>
<body class="home">
  <h2>Welcome YAHYA to JAVA CONFERENCE</h2>
  <form action="#">
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
    <div>
  </form>
</body>
</html>
