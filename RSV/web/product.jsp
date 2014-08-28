<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function addProduct() {
		//return false;
		$.ajax({
			url : "addProduct.action",
			dataType : 'html',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#contentDiv').html('');
				$('#contentDiv').html(res);
			}
		});
	}
	function loadList() {
		$('#sortFormSubmit').click();
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<%-- <script> --%>
<script type="text/javascript">
	
</script>
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading no-print">
			<h2>Product</h2>
		</div>
		<!-- Left navigation -->
		<!-- 		<div class="leftnavigation left"> -->
		<%-- 			<s:include value="leftNavigateMenu.jsp" /> --%>
		<!-- 		</div> -->
		<!-- Left navigation -->

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<s:url action="#" var="addNewProduct" />
			<div class="rightsidecont right">
				<div class="create no-print">
					<s:a href="#" onclick="addProduct()" cssClass="button button-style2 button-mini butcurve-sml">Add New Product</s:a>
				</div>
				<div class="breadcrumb left no-print">
					<a href="loginAction.action">Home</a>- Product
				</div>
				<div class="clear pageheadDiv no-print">
					<div class="pagehead left-red c-red">
						<h2>Products</h2>
					</div>
				</div>
				<div id="formMessage" class="formMessage no-print">
					<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
					
				</div>
				<div class="clear print"><span class="fa-print pointer c-green" onclick="printPage();"></span></div>
				<div class="filterDiv right no-print">
					<s:form id="sortForm" action="productList" method="POST" theme="simple">
					Sort by group : &nbsp;
						<s:select list="productGroupList" id="productGroup" theme="simple" cssClass="ps_select" cssStyle="float:center;" listKey="groupId" listValue="groupName" name="sortId" value="sortId"
							headerKey="0" headerValue="All" onchange="loadList()" />
						<sj:submit formIds="sortForm" id="sortFormSubmit" targets="detailsDiv" cssStyle="display:none;" />
					</s:form>
				</div>
				<!--                details will load here -->
				<div id="detailsDiv"></div>

			</div>

		</div>
	</div>
	<s:hidden id="global" value="product" />
	<s:hidden id="deleteId" />
</div>
<sj:dialog id="deleteConfirm" position="[430,230]" width="400" height="140" buttons="{ 'Ok':function (){deleteOk();}, 'No':function (){deleteNo();} }" title="Delete Confirmation" autoOpen="false"
	modal="true">Are You sure want to delete this product</sj:dialog>

<sj:submit formIds="productForm" id="productFormSubmit" cssStyle="display:none;" targets="contentDiv" />
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<script>
	$(document).ready(function() {
		loadList();
	});
</script>
<s:include value="footer.jsp" />