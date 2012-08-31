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


function confirmCancel(contextPath) {
    contextPath= (contextPath==undefined) ? "": contextPath;
    if(confirm("Are you sure you want to leave this page?")) {
       location.replace(contextPath+"/zombie/conference/user/home");

    }
}