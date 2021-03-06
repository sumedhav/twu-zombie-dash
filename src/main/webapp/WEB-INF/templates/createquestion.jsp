<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
        <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-1.8.1.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/length_validation.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/dynamic_option_adder.js"></script>
        <script>

        </script>
        <title>Zombie Dash : Create Question</title>
    </head>

    <body>
        <div class="container">
            <div class="row-fluid">
                <div class="span12">
                    <h1 class="pageTitle">Zombie Dash</h1>
                    <h3 class="conferenceViewSectionTitle">Create a New Question</h3>
                    <form id="createTask" class="form-horizontal" name="questionForm" method="post" action="${pageContext.request.contextPath}/zombie/gamedesigner/task/${taskId}/create/question">

                        <div class="control-group">
                            <label class="control-label align-left" for="question_text"><span class="error ">*</span>Question:</label>
                                <div class="controls">
                                    <input type="text" name="question_text" id="question_text" onkeyup="limitNumOfCharsInField(question_text, 100, '#questionText_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(question_text, 100, '#questionText_exceed_error');"value="<c:out value="${model.question_text}"/>"
                                        placeholder="enter question"/>
                                    <span id="questionText_field_empty" class="error ">${model.questionText_field_empty}</span>
                                    <span id="questionText_exceed_error" class="error ">${model.questionText_exceed_error}</span>
                                </div>
                        </div>

                        <div class="control-group">
                            <span id="less_than_two_options_error" class ="error ">${model.less_than_two_options_error}</span>
                            <span id="no_option_selected" class ="error ">${model.no_option_selected}</span>
                            <table id="optionTable" name="optionTable">
                                <tr>
                                </tr>
                            </table>
                            <input type="button" id="addOption" class="btn btn-primary" onClick="createOption();" value="Add Option"</input>
                        </div>

                        <div class="control-group">
                            <div class="controls offset6" >
                                 <button name ="addAnotherQuestion" id="addAnotherQuestion" type="submit" class="btn btn-primary" value="true">Add Another Question</button>&nbsp
                                 <button id="submitQuestion" type="submit" class="btn btn-primary">Submit</button>&nbsp
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="controls offset6" >
                            </div>
                        </div>

                    </form>

                </div>
            </div>
        </div>
    </body>
</html>