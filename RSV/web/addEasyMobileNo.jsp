<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<div class="topPanel no-print;">
	<div>Add / Update Easy Mobile No</div>
	<span id="ajaxButton" class="fa-reply  h-skyblue c-skyblue" title="go back" onclick="ajaxCall('loadGroup.action');"></span> &nbsp; <span id="ajaxButton" class="fa-pencil  h-green c-green"
		title="add new user" onclick="ajaxCall('addEasyMobile.action');"></span> &nbsp; <span class="fa-remove-sign h-red c-red" title="close" onclick="closePop();"></span>
</div>
<div class="clear tabledata">

	<s:iterator value="easyMobileNoList" var="mobileList" status="stat" begin="0" end="0">
		<s:form id="mobileForm" name="mobileForm" method="POST" theme="simple" action="addEasyMobileNo">
			<s:hidden name="easyMobileNo.easyMobileId" id="easyMobileId" value="%{easyMobileId}" />
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
							<label>Mobile No</label>
						</p>
						<s:textfield theme="simple" size="20" id="groupName" name="easyMobileNo.easyMobileNo" value="%{easyMobileNo}" />
					</div>
					<div class="top-bottom">
						<p>
							<label>Company Name</label>
						</p>
						<s:select list="companyList" id="companyName" theme="simple" listKey="companyId" listValue="companyName" name="easyMobileNo.company.companyId" value="companyId" />
					</div>
					<div class="top-bottom">
						<p>
							<label>Description</label>
						</p>
						<s:textarea theme="simple" id="mobileDesc" name="easyMobileNo.description" value="%{description}" />
						<s:hidden id="hdnBalance" name="easyMobileNo.easyRechargeBalance.easyRechargeBalance" value="%{easyRechargeBalance.easyRechargeBalance}" />
					</div>
					<div class="top-bottom" style="text-align: right; margin-right: 45px;">
						<s:reset cssClass="button-style1 button-small1 butcurve-sml " theme="simple" value="Cancel" label="Cancel" onclick="closePop();" />
						<sj:submit formIds="mobileForm" cssClass="button-style2 button-small1 butcurve-sml" value="Submit" targets="successMsg" onClickTopics="before" onSuccessTopics="complete"
							onErrorTopics="errorState"></sj:submit>
					</div>
					<div class="clear" style="text-align: center;"></div>
					<div class="top-bottom"></div>
				</div>
			</div>
		</s:form>
	</s:iterator>
	<%--    <s:hidden id="subPage" value="group" /> --%>
</div>