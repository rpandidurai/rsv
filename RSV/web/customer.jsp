<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function addCustomer() {
		//return false;
		$.ajax({
			url : "addCustomer.action",
			dataType : 'html',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#contentDiv').html('');
				$('#contentDiv').html(res);
			}
		});
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea ">
		<div class="pageheading no-print">
			<h2>Customer</h2>
		</div>

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<s:url action="#" var="addNewProduct" />
			<div class="rightsidecont right ">
				<div class="create no-print">
					<s:a href="#" onclick="addCustomer()" cssClass="button button-style2 button-mini butcurve-sml">Add New Customer</s:a>
				</div>
				<div class="breadcrumb left no-print">
					<a href="loginAction.action">Home</a>- Customer
				</div>
				<div class="clear pageheadDiv no-print">
					<div class="pagehead left-green c-green">
						<h2>Customer</h2>
					</div>
				</div>
				<div id="formMessage" class="formMessage no-print">
					<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
				</div>
				<div class="clear print"><span class="fa-print pointer c-green" onclick="printPage();"></span></div>
				<!-- 				details will load here -->
				<div id="detailsDiv"></div>
			</div>

		</div>
	</div>
	<s:hidden id="global" value="customer" />
	<s:hidden id="deleteId" />
</div>
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this customer</sj:dialog>
<sj:a href="customerList" targets="detailsDiv" loadingText="loading..." id="loadCustomerList"></sj:a>
<script>
	$(document).ready(function() {

		$('#loadCustomerList').click();
	});
</script>
<!-- content area -->
<s:include value="footer.jsp" />