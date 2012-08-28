function confirmCancel(contextPath) {
    contextPath= (contextPath==undefined) ? "": contextPath;
    if(confirm("Are you sure you want to leave this page?")) {
        location.replace(contextPath+"/zombie/admin/users");
    }
}