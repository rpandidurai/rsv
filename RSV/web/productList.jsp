<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<div class="clear tabledata">
	<table>
		<tr>
			<th width="10%">S.No</th>
			<th width="15%">Product Name</th>
			<th width="10%">Unit Rate</th>
			<th width="30%">Benefit</th>
			<th width="10%">Validity</th>
			<th width="10%">Group</th>
			<th width="10%" class="no-print">Action</th>
		</tr>
		<s:iterator value="productList" status="stat">
			<tr>
				<td><s:property value="#stat.count" /></td>
				<td><s:property value="productName" /></td>
				<td><s:property value="productUnitRate" /></td>
				<td><s:property value="productBenefits" /></td>
				<td><s:property value="productValidity" /></td>
				<td><s:property value="productGroup.groupName" /></td>
				<td class="no-print"><s:a action="addProduct?filterId=%{productId}">
						<span class="tools icon-pencil green"></span>
					</s:a> &nbsp; <s:a href="javascript:void(0);" onclick="confirmDelete(%{productId});">
						<span class="tools icon-x red"></span>
					</s:a> <sj:a href="deleteProduct.action?filterId=%{productId}" id="deleteId_%{productId}" targets="successMsg" onClickTopics="before" onSuccessTopics="complete" onErrorTopics="errorState">
					</sj:a></td>
			</tr>
		</s:iterator>
	</table>
</div>
