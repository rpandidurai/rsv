<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<div class="topPanel no-print;">
	<div>Add / Update User</div>
	<span id="ajaxButton" class="fa-reply  h-skyblue c-skyblue" title="go back" onclick="ajaxCall('loadUser.action');"></span> &nbsp; <span id="ajaxButton"
		class="fa-pencil  h-green c-green" title="add new user" onclick="ajaxCall('userDetails.action');"></span> &nbsp; <span class="fa-remove-sign h-red c-red" title="close"
		onclick="closePop();"></span>
</div>
<div class="clear tabledata">
	<s:iterator value="userDetailList" var="userList" status="statEntry" begin="0" end="0">
		<s:form id="userForm" name="userForm" method="POST" theme="simple" action="addUser">
			<s:hidden name="userDetail.userId" value="%{userId}"></s:hidden>
			<div class="formdata">
				<div class="formBoxFlat top-green">
					<div id="formMessage" class="formMessage">
						<s:if test="hasActionMessages()">
							<span class="success fa-ok-sign"> <s:iterator value="actionMessages">
									<s:property />
								</s:iterator>
							</span>
						</s:if>
						<s:if test="hasActionErrors()">
							<span class="failed fa-remove-sign"> <s:iterator value="actionErrors">
									<s:property />
								</s:iterator>
							</span>
						</s:if>
						<span class="success fa-ok-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
					</div>
					<div class="top-bottom">
						<p>
							<label>Name</label>
						</p>
						<s:textfield theme="simple" size="20" id="name" name="userDetail.name" value="%{name}" />
					</div>
					<div class="top-bottom">
						<p>
							<label>User name</label>
						</p>
						<s:textfield theme="simple" size="20" id="username" name="userDetail.userName" value="%{userName}" />
					</div>
					<div class="top-bottom">
						<p>
							<label>Password</label>
						</p>
						<s:password theme="simple" size="20" id="password" name="userDetail.passWord" />
					</div>
					<div class="top-bottom">
						<p>
							<label>Password Re-Enter</label>
						</p>
						<s:password theme="simple" size="20" id="rPassword" name="rPassword" />
					</div>
					<div class="top-bottom">
						<p>
							<label>E-mail</label>
						</p>
						<s:textfield theme="simple" size="20" id="email" name="userDetail.email" value="%{email}" />
					</div>
					<div class="top-bottom">
						<p>
							<label>Role</label>
						</p>
						<s:textfield theme="simple" size="20" id="usertype" name="userDetail.userType" value="%{userType}" />
						<%-- 												<s:select headerKey="0" headerValue="Select" list="userRole"></s:select> --%>
					</div>
					<div class="top-bottom" style="text-align: right; margin-right: 45px;">
						<s:reset cssClass="button-style1 button-small1 butcurve-sml" theme="simple" value="Cancel" label="Cancel" onclick="closePop();" />
						<sj:submit formIds="userForm" cssClass="button-style2 button-small1 butcurve-sml" value="Submit" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState"></sj:submit>
					</div>
					<div class="clear"></div>
					<div class="top-bottom"></div>
				</div>
			</div>
		</s:form>
	</s:iterator>
</div>