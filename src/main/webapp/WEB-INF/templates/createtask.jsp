<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%! static public int optionCounter = 1; %>
<html>
    <head>
           <title>Zombie Dash : Create Task</title>
           <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
           <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet"/>
            <h1>Zombie Dash</h1>
            <h3>Create a New Task</h3>
    </head>
    <body>
        <div class="row-fluid">
            <div class="span12">
                 <form id="createTask" class="form-horizontal" name="taskForm" method="post" action="${pageContext.request.contextPath}/zombie/admin/conference/${conferenceId}/create/task">
                     <div class="control-group">
                          <label class="control-label align-left" for="task_name"><span class="error ">*</span>Name:</label>
                          <div class="controls">
                            <input type="text" name="task_name" id="task_name" onkeyup="limitNumOfCharsInField(task_name, 100, '#taskname_exceed_error');"
                               onkeydown="limitNumOfCharsInField(task_name, 100, '#taskname_exceed_error');"value="${model.name}"<c:out value=""/>"
                               placeholder="enter taskname"/>
                            <span class="error field-missing">${model.tasknameFieldEmpty}</span>
                          </div>
                     </div>
                     
                     <div class="control-group">
                         <label class="control-label align-left" for="task_description"><span class="error ">*</span>Description:</label>
                         <div class="controls">
                          <textarea id='task_description' name='task_description' onkeyup="limitNumOfCharsInField(task_description,500,'#descriptionExceedError');"
                                  onkeydown="limitNumOfCharsInField(task_description,500,'#descriptionExceedError');"
                                 placeholder="enter task description"><c:out value="${model.description}"/></textarea>
                              <span class="error field-missing">${model.descriptionFieldMissing}</span>
                          </div>
                   </div>
                   <div class="control-group">
                        <div class="controls offset6" >
                             <button id="addQuestion" type="submit" class="btn btn-primary">Add Question</button>&nbsp
                         </div>
                     </div>
                </form>
            </div>
        </div>
     </body>
</html>