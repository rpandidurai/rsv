<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function addSales() {
		//return false;
		$.ajax({
			url : "addSales.action",
			dataType : 'html',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#contentDiv').html('');
				$('#contentDiv').html(res);
			}
		});
	}
	function loadSalesDetails() {
		$('#filterDateSubmit').click();
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Sales Details</h2>
		</div>
		<!-- Left navigation -->
		<div class="leftnavigation left">
			<s:include value="leftNavigateMenu.jsp" />
		</div>
		<!-- Left navigation -->

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<s:url action="#" var="addNewProduct" />
			<div class="rightsidecont right">
				<div class="create">
					<s:a href="#" onclick="addSales()" cssClass="button button-style2 button-mini butcurve-sml">Enter Sales Details</s:a>
				</div>
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- Sales Details
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead">
						<h2>Sales Book</h2>
					</div>
				</div>
				<div id="formMessage" class="formMessage">
					<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
				</div>
				<div class="clear print"><span class="fa-print pointer c-green" onclick="printPage();"></span></div>
				<div class="filterDiv">
					<s:form id="filterDateForm" action="salesBook" method="POST" theme="simple">                  
                    Filter by date : &nbsp;
                    <sj:datepicker showOn="focus" cssClass="ps_filter_1" id="fromFilterDate" value="today" name="fromFilterDate" readonly="true" changeMonth="true" changeYear="true"
							maxDate="0" onchange="loadSalesDetails();" />
                     - 
                    <sj:datepicker showOn="focus" cssClass="ps_filter_1" id="toFilterDate" value="today" name="toFilterDate" readonly="true" changeMonth="true" changeYear="true" maxDate="0"
							onchange="loadSalesDetails();" />
						<sj:submit formIds="filterDateForm" id="filterDateSubmit" targets="detailsDiv" cssStyle="display:none;"></sj:submit>
					</s:form>
				</div>
				<div id="detailsDiv"></div>
			</div>
		</div>
	</div>
	<s:hidden id="global" value="sales" />
	<s:hidden id="deleteId" />
</div>
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this Sales Entry</sj:dialog>

<sj:submit formIds="SalesDetailsForm" id="salesDetailsFormSubmit" cssStyle="display:none;" targets="contentDiv" />
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<script>
	$(document).ready(function() {
		loadSalesDetails();
	});
</script>
<s:include value="footer.jsp" />