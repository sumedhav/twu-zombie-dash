var count = 0;

function setCount(value) {
    count=value;
}
function getCount(){
 return ++count;
}

function redirectURL(contextPath, destinationUrl) {
    contextPath= (contextPath==undefined) ? "": contextPath;
       location.replace(contextPath+destinationUrl);
}