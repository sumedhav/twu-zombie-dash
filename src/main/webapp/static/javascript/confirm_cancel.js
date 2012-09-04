function confirmCancel(contextPath, destinationUrl) {
    contextPath= (contextPath==undefined) ? "": contextPath;
    if(confirm("Are you sure you want to leave this page?")) {
       location.replace(contextPath+destinationUrl);
    }
}