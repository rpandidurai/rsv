<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<div class="topPanel no-print;">
	<div>User List</div>
	<span class="fa-reply  h-skyblue c-skyblue" title="go back" onclick="closePop();"></span> &nbsp; <span id="ajaxButton" class="fa-pencil  h-green c-green" title="add new user"
		onclick="ajaxCall('userDetails.action');"></span> &nbsp; <span class="fa-remove-sign h-red c-red" title="close" onclick="closePop();"></span>
</div>
<div id="userList">
	<div id="formMessage" class="formMessage">
		<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
	</div>
	<table>
		<tr>
			<th width="10%">S.No</th>
			<th width="15%">User Name</th>
			<th width="10%">Name</th>
			<th width="30%">Email</th>
			<th width="10%">Status</th>
			<th width="10%">User Type</th>
			<th width="10%" class="no-print">Action</th>
		</tr>
		<s:iterator value="userDetailList" status="stat">
			<tr>
				<td><s:property value="#stat.count" /></td>
				<td><s:property value="userName" /></td>
				<td><s:property value="name" /></td>
				<td><s:property value="email" /></td>
				<td><s:property value="status" /></td>
				<td><s:property value="userType" /></td>
				<td class="no-print"><s:a onclick="ajaxCall('userDetails.action?filterId=%{userId}');" href="">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{userId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deleteUser.action?filterId=%{userId}" id="deleteId_%{userId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>
<s:hidden id="subPage" value="loadUser" />
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this User</sj:dialog>