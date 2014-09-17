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
					&#x20B9;
					<s:property value="easyRechargeBalance" />
				</div>
				<p>Last transaction on</p>
				<span><s:property value="lastTxnDate" /></span>
				<p>Downloads</p>
				<span>3</span>
				<p>Forums</p>
				<span>Courses Only</span>
				<button onclick="ajaxCall('easyRechargeDetails.action?filterId=1')">Load Cash</button>
			</div>
		</div>
	</s:iterator>
</div>