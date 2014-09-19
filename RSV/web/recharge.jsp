<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<head>
<link rel="stylesheet" href="css/easyList.css" type="text/css" />
</head>
<script>
	function loadEasyRechargeDetails() {
		$.ajax({
			url : "easyRechargeDetails.action",
			dataType : "html",
			type : "POST",
			async : true,
			success : function(res) {
				$('#detailsDiv').html('');
				$('#detailsDiv').html(res);
			}
		});
	}
	function ajaxCall(url) {
		$.ajax({
			url : url,
			dataType : 'html',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#popDiv').html('');
				$('#popDiv').html(res);
				$('#fade').show('slow');
				$('#light').show('slow');
			}
		});
	}
	function closePop() {
		$('#fade').hide('slow');
		$('#light').hide('slow');
		$('#popDiv').html('');
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Easy Recharge</h2>
		</div>
		<!-- Right side table & Form -->
		<div id="contentDiv">
			<div class="rightsidecont right">
				<div class="create">
					<s:a href="#" onclick="addPurchase()" cssClass="button button-style2 button-mini butcurve-sml">Enter Purchase Details</s:a>
				</div>
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>-Easy Recharge
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead">
						<h2>Easy Recharge</h2>
					</div>
				</div>
				<!-- 				<div id="formMessage" class="formMessage"> -->
				<%-- 					<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span> --%>
				<!-- 				</div> -->
				<div class="clear padd-10"></div>
				<div onclick="ajaxCall('loadMobileForm.action');" style="text-align: center;"> <button class="btn-lvl-1 b-skyBlue">Add new mobile number</button></div>
				<div class="clear padd-10"></div>
				<div id="detailsDiv"></div>
				<!-- 				<div class="content-container no-print"></div> -->
			</div>
		</div>
	</div>
	<s:hidden id="global" value="easyRecharge" />
	<s:hidden id="deleteId" />
</div>
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this Purchase Entry</sj:dialog>
<sj:submit formIds="PurchaseDetailsForm" id="purchaseDetailsFormSubmit" cssStyle="display:none;" targets="contentDiv" />
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<script>
	$(document).ready(function() {
		loadEasyRechargeDetails();
	});
</script>
<div id="light" class="rightsidecont right white_content popDiv ">
	<div id="popDiv" class="clear tabledata"></div>
</div>
<s:include value="footer.jsp" />