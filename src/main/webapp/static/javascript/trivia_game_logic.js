function validate(numQns,errorName){
    var radios = document.getElementsByTagName('input');
    var value=0;
    for (var i = 0; i < radios.length; i++) {
        if (radios[i].type === 'radio' && radios[i].checked) {
            value++;
        }
    }
    if (value < numQns) {
      $(errorName).html("<br />You need to answer all the questions!");
      return false;
      }
    else {
      return true;
    }
  }

function isMobileDevice(){
if((navigator.userAgent.match(/iPhone/i)) ||
         (navigator.userAgent.match(/Android/i))  || (navigator.userAgent.match(/iPod/i))){
         return true;
         }
         return false;
}

function doExpandCollapse(id,image,contextPath){
    var descriptionLink = document.getElementById(id);
    var helpImage = document.getElementById(image);
    if (descriptionLink.style.display=='none'){
        descriptionLink.style.display='';
        helpImage.src=contextPath+'/static/images/downArrow.jpg';
    }
    else{
        descriptionLink.style.display='none';
        helpImage.src=contextPath+'/static/images/sideArrow.jpg';
    }
}