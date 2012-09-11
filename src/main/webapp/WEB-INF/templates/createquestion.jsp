<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/try.js"></script>
<script>
function createOption(){
    var c = getCount();
    setCount(c);
    var tdata ="<tr name=\"row_"+c+"\">" +
    "<td class=\"controls\">" +
    "<input type=\"radio\" name=\"isCorrect\" id=\"isCorrect"+c+"\"/>" +
    "</td>" +
    "<td class=\"controls\">" +
    "<input type=\"text\" name=\"optionText_"+c+"\" id=\"optionText_"+c+"\" onkeyup=\"limitNumOfCharsInField(questionText, 100, \'#optionText_exceed_error\');\" onkeydown=\"limitNumOfCharsInField(optionText, 100, \'#questionText_exceed_error\');\" value=\"<c:out value=""/>\" placeholder=\"enter option "+c+"\"/>" +
    "<span id=\"optionText_field_empty\" class=\"error \">${optionTextFieldEmpty}</span>" +
    "<span id=\"optionText_exceed_error\" class=\"error \"></span>"+
    "</td>" +
    "</tr>";
    var tdataElement = jQuery(tdata);
    jQuery('#optionTable > tbody:last').append(tdataElement);
}
</script>

    <head>
           <title>Zombie Dash : Create Question</title>
           <link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
           <link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet"/>
           <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/createConference.js"></script>
           <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/dynamic_option_counter.js"></script>
           <h1>Zombie Dash</h1>
           <h3>Create a New Question</h3>
    </head>
    <body>
            <div class="row-fluid">
                <div class="span12">
                     <form id="createTask" class="form-horizontal" name="createQuestion" method="post" action="${pageContext.request.contextPath}/zombie/admin/conference/${conferenceId}/create/question">
                     <div class="control-group">
                                              <label class="control-label align-left" for="questionText"><span class="error ">*</span>Question:</label>
                                              <div class="controls">
                                                <input type="text" name="questionText" id="questionText" onkeyup="limitNumOfCharsInField(questionText, 100, '#questionText_exceed_error');"
                                                   onkeydown="limitNumOfCharsInField(questionText, 100, '#questionText_exceed_error');"value="<c:out value=""/>"
                                                   placeholder="enter question"/>
                                                <span id="questionText_field_empty" class="error ">${questionTextFieldEmpty}</span>
                                                <span id="questionText_exceed_error" class="error "></span>
                                              </div>
                                         </div>

                                         <div class="control-group">
                                             <table id="optionTable" name="optionTable">
                                                 <tr>
                                                 </tr>
                                             </table>
                                             <input type="button" id="addOption" class="btn btn-primary" onClick=" javascript:createOption();" value="Add Option"</input>                    </form>
                                 </div>
                             </div>
                          </body>
                     </html>