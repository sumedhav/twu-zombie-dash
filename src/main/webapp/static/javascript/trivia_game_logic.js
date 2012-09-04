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