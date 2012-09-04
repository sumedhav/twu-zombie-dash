<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Customer Home</title>
    <script type="text/javascript">
     if(!((navigator.userAgent.match(/iPhone/i)) ||
             (navigator.userAgent.match(/iPod/i)) ||
             (navigator.userAgent.match(/Android/i)) ) || (navigator.userAgent.match(/iPod/i))){
             document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">')
             document.write('<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">')
             }
    </script>
      <meta name="viewport" content="width=device-width">

</head>

<body>
     <div class="container">
        <h1 class="pageTitle">
            <div>Welcome Customer!!!</div>
        </h1>
        <div class="sectionTitle">
            <a href="${pageContext.request.contextPath}/zombie/conference/user/game">Play Game</a>
            <!--Your score is <b id="obtainedScore">${obtainedScore}</b> out of <b id="maxScore">${maxScore}</b>. -->
        </div>
     </div>
</body>
</html>