<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Create Conference Page</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/create_conference.js"></script>

<link type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/static/css/zombie.css" rel="stylesheet">

</head>
<body onload='document.conferenceCreationForm.conf_name.focus();'>
	 <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>
          	<div class="sectionTitle">Create Conference: Enter Details</div>
             <div class="control-group">
                  <div id="message_to_be_displayed" class="form-message error">
                      ${model.errorString}
                  </div>
             </div>



             <div class="row-fluid">
                 <div class="span12">
                     <form id="conferenceForm" class="form-horizontal" name="conferenceForm" action="${pageContext.request.contextPath}/zombie/admin/conference/submit" method = "POST">

                         <div class="control-group">
                             <label class="control-label align-left" for="ConferenceName">Conference name:</label>
                             <div class="controls">
                                <textarea name='conf_name' onkeyup="limitNumOfCharsInField(conf_name, 100, '#nameExceedError');"
                                   placeholder="enter conference name">${model.name}</textarea>
                                <span class="error inline-help">${model.nameFieldMissing}</span>
                                <span id="nameExceedError" class="error inline-help" style="color:#FF0000"></span>
                             </div>
                         </div>

                         <div class="control-group">
                              <label class="control-label align-left" for="ConferenceTopic">Topic:</label>
                              <div class="controls">
                                 <textarea name='conf_topic' onkeyup="limitNumOfCharsInField(conf_topic, 100, '#topicExceedError');"
                                     placeholder="enter conference topic">${model.topic}</textarea>
                                 <span class="error inline-help">${model.topicFieldMissing}</span>
                                 <span id="topicExceedError" class="error inline-help" style="color:#FF0000"></span>
                              </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceStartDate">Start date:</label>
                                <div class="controls">
                                 <input type='text' name='conf_start_date' size="28" placeholder="yyyy-mm-dd" value=${model.startDate} >
                                 <span class="error inline-help">${model.startDateFieldMissing}</span>
                                 <span class="error inline-help">${model.startDateError}</span>
                                </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceEndDate"> End date:</label>
                               <div class="controls">
                                <input type='text' name='conf_end_date' size="28" placeholder="yyyy-mm-dd" value=${model.endDate}>
                                 <span class="error inline-help">${model.endDateFieldMissing}</span>
                                  <span class="error inline-help">${model.endDateError}</span>

                                </div>
                          </div>


                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceDescription">Description:</label>
                                <div class="controls">
                                 <textarea name='conf_description' onkeyup="limitNumOfCharsInField(conf_description,500,'#descriptionExceedError');"
                                        placeholder="enter conference description">${model.description}</textarea>
                                     <span class="error inline-help">${model.descriptionFieldMissing}</span>
                                     <span id="descriptionExceedError" class="error inline-help">${model.descriptionError}</span>
                                 </div>
                          </div>


                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceTopic">Venue:</label>
                                <div class="controls">
                                    <textarea name='conf_venue' onkeyup="limitNumOfCharsInField(conf_venue,200,'#venueExceedError');"
                                          placeholder="enter conference venue">${model.venue}</textarea>
                                       <span class="error inline-help">${model.venueFieldMissing}</span>
                                       <span id="venueExceedError" class="error inline-help">${model.venueError}</span>
                                </div>
                          </div>

                          <div class="control-group">
                                <label class="control-label align-left" for="ConferenceTopic">Max no. of attendees:</label>
                                <div class="controls">
                                    <input type='text' name='conf_max_attendees' size="28" placeholder="enter max no. of attendees" value=${model.maxAttendees}>
                                       <span class="error inline-help">${model.maxAttendeesFieldMissing}</span>
                                       <span class="error inline-help">${model.numberError}</span>
                                </div>
                          </div>


                         <div class="control-group">
                              <div class="controls offset6" >
                                 <button type="submit" class="btn btn-primary">Save</button>
                              </div>
                          </div>

                         <div class="control-group">
                            <div class="controls offset6">
                            <input type="button" value="Cancel" class="btn btn-primary"
                               onclick="return confirmCancel('${pageContext.request.contextPath}')"/>
                            </div>
                        </div>

                     </form>
                 </div>
             </div>
         </div>


	</form>

</body>
</html>
