<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>

<div class="clear tabledata">
	<table>
		<tr>
			<th width="5%">S.No</th>
			<th width="22%">Customer Name</th>
			<th width="15%">Area</th>
			<th width="30%">Address</th>
			<th width="10%">Phone No</th>
			<th width="8%" class="no-print">Action</th>
		</tr>
		<s:iterator value="customerList" status="stat">
			<tr>
				<td><s:property value="#stat.count" /></td>
				<td><s:property value="customerName" /></td>
				<td><s:property value="customerArea" /></td>
				<td><s:property value="customerAddress" /></td>
				<td><s:property value="customerPhoneNo" /></td>
				<td class="no-print"><s:a action="addCustomer?filterId=%{customerId}">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{customerId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deleteCustomer?filterId=%{customerId}" id="deleteId_%{customerId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>