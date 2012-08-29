function deleteUser(contextPath, userName) {
    contextPath= (contextPath==undefined) ? "": contextPath;
    if(confirm("Are you sure you want to delete this user?")) {
        location.replace(contextPath+"/zombie/admin/users/deleteuser/"+userName);
    }
}