<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>

<div class="clear tabledata">
	<table>
		<tr>
			<th colspan="8" class="head center">Sales Book <s:date name="fromFilterDate" format="dd/MM/YYYY" /> - <s:date name="toFilterDate" format="dd/MM/YYYY" /></th>
		</tr>
		<tr>
			<th width="8%">S.No</th>
			<th width="15%">Customer Name</th>
			<th width="15%">Delivery Boy Name</th>
			<th width="10%">Date</th>
			<th width="10%">Total Quantity</th>
			<th width="10%">Total Amount</th>
			<th width="12%" class="center">Bill No</th>
			<th width="10%" class="no-print">Action</th>
		</tr>
		<s:iterator value="salesEntryList" status="stat">
			<tr>
				<td><s:property value="#stat.count" /></td>
				<td><s:property value="customerDetails.customerName" /></td>
				<td><s:property value="deliveryBoys.deliveryName" /></td>
				<td><s:date name="entryDate" format="dd/MM/yyyy" /></td>
				<td><s:property value="totalQuantity" /></td>
				<td><s:property value="totalAmount" /></td>
				<td><s:property value="billNo" /></td>
				<td  class="no-print"><s:a action="addSales?filterId=%{salesEntryId}">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{salesEntryId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deleteSalesEntry.action?filterId=%{salesEntryId}" id="deleteId_%{salesEntryId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>