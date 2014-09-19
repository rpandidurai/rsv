<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>

<div class="clear easyContainer">
	<s:iterator value="easyRechargeBalanceList" status="stat">
		<div>
			<div class="silver">
				<h1>
					<s:property value="easyMobileNo.easyMobileNo" />
				</h1>
				<h2>
					<s:property value="easyMobileNo.company.companyName" />
				</h2>
				<div class="price">
					<b class="fa-inr"><s:property value="easyRechargeBalance" /></b>
				</div>
				<p>Last transaction on</p>
				<span><s:date name="lastTxnDate" format="dd MMM yyyy" /></span>
				<p>Downloads</p>
				<span>3</span>
				<p>Forums</p>
				<span>Courses Only</span>
				<button onclick="ajaxCall('easyRechargeDetails.action?filterId=<s:property value="easyMobileNo.easyMobileId" />')">Load Cash</button>
			</div>
		</div>
	</s:iterator>
</div>