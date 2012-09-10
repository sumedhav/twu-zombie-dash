<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Create Conference Page</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery-1.8.1.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/createConference.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/confirm_cancel.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet"/>
<link type="text/css" href="${pageContext.request.contextPath}/static/css/custom.datepick.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/jquery.datepick.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/util.js"></script>

</head>
<body onload='document.conferenceForm.conf_name.focus();'>
	 <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>
             <div style="display: none;">
                 <img id="calImg" width="27" height="27" src="${pageContext.request.contextPath}/static/images/Calendar.jpg" alt="Popup" class="trigger">
             </div>
          	<div class="sectionTitle">Create Conference: Enter Details</div>
             <div class="control-group">
                  <div class="form-message">
                      <b>All (<span class="error ">*</span>) fields are mandatory.</b>
                  </div>
             </div>

             <div class="row-fluid">
                 <div class="span12">
                     <form id="conferenceForm" class="form-horizontal" name="conferenceForm" action="${pageContext.request.contextPath}/zombie/admin/conference/create" method = "POST">

                         <div class="control-group">
                             <label class="control-label align-left" for="ConferenceName"><span class="error ">*</span>Conference &#160;name:</label>
                             <div class="controls">
                                <textarea id='conf_name' name='conf_name' onkeyup="limitNumOfCharsInField(conf_name, 100, '#nameExceedError');"
                                   onkeydown="limitNumOfCharsInField(conf_name, 100, '#nameExceedError');"
                                   placeholder="enter conference name"><c:out value="${model.name}"/></textarea>
                                <span  id="errorConferenceName" class="error  field-missing">${model.nameFieldMissing}</span>
                                <span id="nameExceedError" class="error "></span>
                             </div>
                         </div>

                         <div class="control-group">
                              <label class="control-label align-left" for="ConferenceTopic"><span class="error ">*</span>Topic:</label>
                              <div class="controls">
                                 <textarea id='conf_topic' name='conf_topic' onkeyup="limitNumOfCharsInField(conf_topic, 100, '#topicExceedError');"
                                     onkeydown="limitNumOfCharsInField(conf_topic, 100, '#topicExceedError');"
                                     placeholder="enter conference topic"><c:out value="${model.topic}"/></textarea>
                                 <span class="error  field-missing">${model.topicFieldMissing}</span>
                                 <span id="topicExceedError" class="error "></span>
                              </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceStartDate"><span class="error ">*</span>Start date:</label>
                                <div class="controls">
                                 <input type='text' id="conf_start_date" name='conf_start_date' placeholder="yyyy-mm-dd" onFocus="javascript" value="<c:out value="${model.startDate}"/>" >
                                 <span class="error  date-field-missing">${model.startDateFieldMissing}</span>
                                 <div class="error ">${model.startDateError}</div>
                                </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceEndDate"><span class="error ">*</span>End date:</label>
                               <div class="controls">
                                <input type='text' id="conf_end_date" name='conf_end_date' placeholder="yyyy-mm-dd" onFocus="javascript" value="<c:out value="${model.endDate}"/>">
                                 <span class="error  date-field-missing">${model.endDateFieldMissing}</span>
                                 <div class="error ">${model.endDateError}</div>

                                </div>
                          </div>


                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceDescription"><span class="error ">*</span>Description:</label>
                                <div class="controls">
                                 <textarea id='conf_description' name='conf_description' onkeyup="limitNumOfCharsInField(conf_description,500,'#descriptionExceedError');"
                                         onkeydown="limitNumOfCharsInField(conf_description,500,'#descriptionExceedError');"
                                        placeholder="enter conference description"><c:out value="${model.description}"/></textarea>
                                     <span class="error  field-missing">${model.descriptionFieldMissing}</span>
                                     <span id="descriptionExceedError" class="error ">${model.descriptionError}</span>
                                 </div>
                          </div>


                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceTopic"><span class="error ">*</span>Venue:</label>
                                <div class="controls">
                                    <textarea id='conf_venue' name='conf_venue' onkeyup="limitNumOfCharsInField(conf_venue,200,'#venueExceedError');"
                                          onkeydown="limitNumOfCharsInField(conf_venue,200,'#venueExceedError');"
                                          placeholder="enter conference venue"><c:out value="${model.venue}"/></textarea>
                                       <span class="error  field-missing">${model.venueFieldMissing}</span>
                                       <span id="venueExceedError" class="error ">${model.venueError}</span>
                                </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceAttendees"><span class="error ">*</span>Max no. of &#160;attendees:</label>
                                <div class="controls">
                                    <input type='text' id='conf_max_attendees' name='conf_max_attendees' size="28" placeholder="enter max no. of attendees"
                                     onkeyup="limitMaxNumberOfAttendees(6,'#maxAttendeesError');" value="<c:out value="${model.maxAttendees}"/>"
                                     onkeydown="limitMaxNumberOfAttendees(6,'#maxAttendeesError');"  />
                                       <span class="error  field-missing">${model.maxAttendeesFieldMissing}</span>
                                       <span id="maxAttendeesError" class="error "></span>
                                       <div class="error ">${model.numberError}</div>
                                </div>
                          </div>


                         <div class="control-group">
                              <div class="controls offset6" >
                                 <button id="submit" type="submit" class="btn btn-primary">Save</button>&nbsp
                                  <input type="button" id="cancel" value="Cancel" class="btn btn-primary"
                                      onclick="return confirmCancel('${pageContext.request.contextPath}','/zombie/admin/conference/list')"/>
                              </div>
                          </div>

                     </form>
                 </div>
             </div>
         </div>


	</form>

</body>
</html>
