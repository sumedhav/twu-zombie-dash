<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<t:form>
    <jsp:attribute name="title">Zombie Dash : Attendee Registration</jsp:attribute>
    <jsp:body>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/javascript/createAttendee.js"></script>
     <div class="container">
             <h1 class="pageTitle">
                 <div>Zombie Dash</div>
             </h1>
             <h3>Enter Registration Details</h3>

             <div class="control-group">
                <div class="form-message">
                    <b>All (<span class="error ">*</span>) fields are mandatory.</b>
                </div>
                <div name="error_message_div" id="error_message_div" class="error">
                    <b>
                    ${validationMessage}
                    </b>
                </div>
             </div>


             <div class="row-fluid">
                 <div class="span12">
                     <form id="attendeeRegistration" class="form-horizontal" name="attendeeRegistration" method="post" action="${pageContext.request.contextPath}/zombie/register/${model.confId}">
                         <div class="control-group">
                              <label class="control-label align-left" for="userName"><span class="error ">*</span>Username:</label>
                              <div class="controls">
                                <input type="text" name="userName" id="userName" onkeyup="limitNumOfCharsInField(userName, 40, '#username_exceed_error');"
                                   onkeydown="limitNumOfCharsInField(userName, 40, '#username_exceed_error');"value="<c:out value="${model.username}"/>"
                                   placeholder="enter username"/>
                                <span id="username_field_empty" class="error ">${usernameFieldEmpty}</span>
                                <span id="username_exceed_error" class="error "></span>
                                <div id="invalid_user_name" class="error ">${invalidUserName}</div>
                              </div>
                         </div>
                        <div class="control-group">
                               <label class="control-label align-left" for="password"><span class="error ">*</span>Password:</label>
                               <div class="controls">
                                 <input type="password" name="password" id="password" onkeyup="limitNumOfCharsInField(password, 40, '#password_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(password, 40, '#password_exceed_error');" value="<c:out value="${model.password}"/>" placeholder="enter password"/>
                                 <span id="password_field_empty" class="error ">${passwordFieldEmpty}</span>
                                 <span id="password_exceed_error" class="error "></span>
                                 <div id="invalid_password" class="error ">${invalidPassword}</div>
                               </div>
                        </div>
                        <div class="control-group">
                               <label class="control-label align-left" for="password"><span class="error ">*</span>Retype Password:</label>
                               <div class="controls">
                                 <input type="password" name="password2" id="password2" onkeyup="limitNumOfCharsInField(password, 40, '#password_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(password2, 40, '#password_exceed_error');" value="<c:out value="${model.password}"/>" placeholder="enter password"/>
                                 <span id="password_field_empty" class="error ">${passwordFieldEmpty}</span>
                                 <span id="password_exceed_error" class="error "></span>
                                 <div id="invalid_password" class="error ">${invalidPassword}</div>
                               </div>
                        </div>
                         <div class="control-group">
                             <label class="control-label align-left" for="email"><span class="error ">*</span>Email:</label>
                             <div class="controls">
                                <input type="text" name="email" id="email" onkeyup="limitNumOfCharsInField(email, 100, '#email_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(email, 100, '#email_exceed_error');" value="<c:out value="${model.email}"/>" placeholder="enter email"/>
                                <span id="email_field_empty" class="error ">${emailFieldEmpty}</span>
                                <span id="email_exceed_error" class="error "></span>
                                <div id="invalid_email" class="error ">${invalidEmail}</div>
                             </div>
                         </div>
                        <div class="control-group">
                                <label class="control-label align-left" for="name"><span class="error ">*</span>Name:</label>
                                <div class="controls">
                                  <input type="text" name="fullName" id="name" onkeyup="limitNumOfCharsInField(fullName, 40, '#name_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(fullName, 40, '#name_exceed_error');" value="<c:out value="${model.fullName}"/>" placeholder="enter name"/>
                                  <span id="name_field_empty" class="error ">${nameFieldEmpty}</span>
                                  <span id="name_exceed_error" class="error "></span>
                                  <div id="invalid_name" class="error ">${invalidName}</div>
                                </div>
                        </div>
                        <div class="control-group">
                                <label class="control-label align-left" for="dob"><span class="error ">*</span>Date of Birth:</label>
                                <div class="controls">
                                  <input type="text" name="dob" id="dob" onkeyup="limitNumOfCharsInField(dob, 40, '#name_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(dob, 40, '#name_exceed_error');" value="<c:out value="${model.dob}"/>" placeholder="enter date of birth"/>
                                  <span id="dob_field_empty" class="error ">${dobFieldEmpty}</span>
                                  <span id="dob_exceed_error" class="error "></span>
                                  <div id="invalid_dob" class="error ">${invalidDob}</div>
                                </div>
                        </div>
                        <div class="control-group">
                                <label class="control-label align-left" for="phoneNo"><span class="error ">*</span>Phone Number::</label>
                                <div class="controls">
                                  <input type="text" name="phoneNo" id="phoneNo" onkeyup="limitNumOfCharsInField(phoneno, 40, '#name_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(phoneNo, 40, '#name_exceed_error');" value="<c:out value="${model.phoneNo}"/>" placeholder="enter phone number"/>
                                  <span id="phoneno_field_empty" class="error ">${phonenoFieldEmpty}</span>
                                  <span id="phoneno_exceed_error" class="error "></span>
                                  <div id="invalid_phoneno" class="error ">${invalidPhoneno}</div>
                                </div>
                        </div>
                        <div class="control-group">
                                <label class="control-label align-left" for="address"><span class="error ">*</span>Address:</label>
                                <div class="controls">
                                  <input type="text" name="address" id="address" onkeyup="limitNumOfCharsInField(address, 40, '#name_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(address, 40, '#name_exceed_error');" value="<c:out value="${model.address}"/>" placeholder="enter address"/>
                                  <span id="address_field_empty" class="error ">${addressFieldEmpty}</span>
                                  <span id="address_exceed_error" class="error "></span>
                                  <div id="invalid_address" class="error ">${invalidAddress}</div>
                                </div>
                        </div>
                        <div class="control-group">
                                <label class="control-label align-left" for="zipcode"><span class="error ">*</span>Zipcode:</label>
                                <div class="controls">
                                  <input type="text" name="zipcode" id="zipcode" onkeyup="limitNumOfCharsInField(zipcode, 40, '#name_exceed_error');"
                                        onkeydown="limitNumOfCharsInField(zipcode, 40, '#name_exceed_error');" value="<c:out value="${model.zipcode}"/>" placeholder="enter zipcode"/>
                                  <span id="zipcode_field_empty" class="error ">${zipcodeFieldEmpty}</span>
                                  <span id="zipcode_exceed_error" class="error "></span>
                                  <div id="invalid_zipcode" class="error ">${invalidZipcode}</div>
                                </div>
                        </div>
                         <div class="control-group">
                            <label class="control-label align-left" for="country">Country:</label>
                            <div class="controls">
                              <jsp:include page="countrylist.html"/>
                              </div>
                         </div>
                         <div class="control-group">
                            <div class="controls offset6" >
                                 <input id="submit" name="submit" type="submit" value="Save"  class="btn btn-primary" />
                                <input id="cancel" name="cancel" type="button" value="Cancel"
                                onClick="return confirmCancel('${pageContext.request.contextPath}','/zombie/admin/users/list')"
                                class="btn btn-primary"/>
                            </div>
                         </div>
                     </form>
                 </div>
             </div>
         </div>
    </form>
    </jsp:body>
</t:form>
