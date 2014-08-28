<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function addPurchase() {
		//return false;
		$.ajax({
			url : "addPurchase.action",
			dataType : 'html',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#contentDiv').html('');
				$('#contentDiv').html(res);
			}
		});
	}
	function loadPurchaseDetails() {

		$('#filterDateSubmit').click();
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading no-print">
			<h2>Purchase Book</h2>
		</div>

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<s:url action="#" var="addNewProduct" />
			<div class="rightsidecont right">
				<div class="create no-print">
					<s:a href="#" onclick="addPurchase()" cssClass="button button-style2 button-mini butcurve-sml">Enter Purchase Details</s:a>
				</div>
				<div class="breadcrumb left no-print">
					<a href="loginAction.action">Home</a>- Purchase Book
				</div>
				<div class="clear pageheadDiv no-print">
					<div class="pagehead">
						<h2>Purchase Book</h2>
					</div>
				</div>
				<div id="formMessage" class="formMessage no-print">
					<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
				</div>
				<div class="clear print"><span class="fa-print pointer c-green" onclick="printPage();"></span></div>
				<div class="filterDiv no-print">
					<s:form id="filterDateForm" action="purchaseDetails" method="POST" theme="simple">                  
                    Filter by date : &nbsp;
                    <sj:datepicker showOn="focus" cssClass="ps_filter_1" id="fromFilterDate" readonly="true" value="today" name="fromFilterDate" changeMonth="true" changeYear="true" maxDate="0"
							onchange="loadPurchaseDetails();" />
                     - 
                    <sj:datepicker showOn="focus" cssClass="ps_filter_1" id="toFilterDate" readonly="true" value="today" name="toFilterDate" changeMonth="true" changeYear="true" maxDate="0"
							onchange="loadPurchaseDetails();" />
						<sj:submit formIds="filterDateForm" id="filterDateSubmit" targets="detailsDiv" cssStyle="display:none;"></sj:submit>
					</s:form>
				</div>
				<div id="detailsDiv"></div>
			</div>
		</div>
	</div>
	<s:hidden id="global" value="purchase" />
	<s:hidden id="deleteId" />
</div>
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this Purchase Entry</sj:dialog>
<sj:submit formIds="PurchaseDetailsForm" id="purchaseDetailsFormSubmit" cssStyle="display:none;" targets="contentDiv" />
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<script>
	$(document).ready(function() {
		loadPurchaseDetails();
	});
</script>
<s:include value="footer.jsp" />