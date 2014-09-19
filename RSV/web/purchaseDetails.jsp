<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<!-- <div class="clear"> -->
<!-- 	<table class="table-header"> -->
<tr>
	<!-- 	<td colspan="4" class="dark center">Purchase Book</td> -->
</tr>
<!-- 		<tr> -->
<!-- 			<td class="dark">From</td> -->
<!-- 			<td>07/2/2014</td> -->
<!-- 			<td class="dark">To</td> -->
<!--             <td>07/2/2014</td> -->
<!-- 		</tr> -->

<!-- 	</table> -->
<!-- </div> -->
<div class="clear tabledata">
	<table>
		<tr>
			<th colspan="7" class="head center">Purchase Book <s:date name="fromFilterDate" format="dd/MM/YYYY" /> - <s:date name="toFilterDate" format="dd/MM/YYYY" /></th>
		</tr>
		<tr>
			<th width="5%">S.No</th>
			<th width="25%">Invoice No</th>
			<th width="12%">Date</th>
			<th width="15%">Company Name</th>
			<th width="10%">Total Quantity</th>
			<th width="13%">Total Amount</th>
			<th width="10%" class="no-print">Action</th>
		</tr>
		<s:iterator value="purchaseEntryList" status="stats">
			<tr>
				<td><s:property value="#stats.count" /></td>
				<td><s:property value="invoiceNo" /></td>
				<td><s:date name="invoiceDate" format="dd/MM/yyyy" /></td>
				<td><s:property value="company.companyName" /></td>
				<td><s:property value="totalQuantity" /></td>
				<td><span class="fa-inr badge bg-info"><s:property value="totalAmount" /></span></td>
				<td class="no-print"><s:a action="addPurchase?filterId=%{purchaseId}">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{purchaseId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deletePurchase?filterId=%{purchaseId}" id="deleteId_%{purchaseId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>