<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<div class="topPanel no-print">
	<div>Product Group List</div>
	<span id="ajaxButton" class="fa-reply  h-skyblue c-skyblue" title="go back" onclick="closePop();"></span> &nbsp; <span id="ajaxButton" class="fa-pencil  h-green c-green"
		title="add new product group" onclick="ajaxCall('group.action');"></span> &nbsp; <span class="fa-remove-sign h-red c-red" title="close" onclick="closePop();"></span>
</div>
<div id="productGroupList">
	<div id="formMessage" class="formMessage">
		<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
	</div>

	<table>
		<tr>
			<th width="10%">S.No</th>
			<th width="15%">Group Name</th>
			<th width="10%">Action</th>
		</tr>
		<s:iterator value="productGroupList" status="stat">
			<tr>
				<td><s:property value="#stat.count" /></td>
				<td><s:property value="groupName" /></td>
				<td><s:a onclick="ajaxCall('group.action?filterId=%{groupId}');" href="">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{groupId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deleteGroup.action?filterId=%{groupId}" id="deleteId_%{groupId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>
<s:hidden id="subPage" value="loadGroup" />
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this product group</sj:dialog>