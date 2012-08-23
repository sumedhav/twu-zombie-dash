<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <header> ZOMBIE DASH </header>
  <title>Welcome to Trivia Game!</title>
</head>
<body class="home">
  <h2>Welcome to the trivia game </h2>
  <form action="#">
    <c:forEach var="question" items="${questions}" varStatus="questionStatus">
    <h3>${question.text}</h3>
      <c:forEach var="option" items="${question.options}" varStatus="optionStatus">
        <div>
          <input name="question_${questionStatus.count}" id="option_${questionStatus.count}_${optionStatus.count}" type="radio" />
          <label for="option_${questionStatus.count}_${optionStatus.count}">${option.text}</label>
        </div>
      </c:forEach>
    </c:forEach>
    <input type="submit" value="SUBMIT" />
  </form>
</body>
</html>
