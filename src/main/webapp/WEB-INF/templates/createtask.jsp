<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet"/>

        <title>Zombie Dash : Create Task</title>
    </head>

    <body>
        <div class="container">
            <div class="row-fluid">
                <div class="span12">

                    <h1 class="pageTitle">Zombie Dash</h1>
                    <h3 class="conferenceViewSectionTitle">Create a New Task</h3>

                    <form id="createTask" class="form-horizontal" name="taskForm" method="post" action="${pageContext.request.contextPath}/zombie/gamedesigner/conference/${conferenceId}/create/task">

                        <div class="control-group">
                            <label class="control-label align-left" for="task_name"><span class="error ">*</span>Name:</label>

                            <div class="controls">
                                <input type="text" name="task_name" id="task_name" onkeyup="limitNumOfCharsInField(task_name, 100, '#taskname_exceed_error');"
                                    onkeydown="limitNumOfCharsInField(task_name, 100, '#taskname_exceed_error');" placeholder="enter task name"/>
                                    <c:out value="${model.name}"/></input>
                                    <span class="error field-missing">${model.tasknameFieldEmpty}</span>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label align-left" for="task_description"><span class="error ">*</span>Description:</label>
                            <div class="controls">
                                <textarea id='task_description' name='task_description' onkeyup="limitNumOfCharsInField(task_description,500,'#description_exceed_error');"
                                    onkeydown="limitNumOfCharsInField(task_description,500,'#description_exceed_error');"
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
        </div>
     </body>
</html>