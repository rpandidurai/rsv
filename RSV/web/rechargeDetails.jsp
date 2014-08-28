<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function addRechageDetails() {
		//return false;
		$.ajax({
			url : "addRecharge.action",
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
	<div class="contentarea">
		<div class="pageheading">
			<h2>Recharge Details</h2>
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
					<s:a href="#" onclick="addRechageDetails()" cssClass="button button-style2 button-mini butcurve-sml">Enter Recharge Details</s:a>
				</div>
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- Recharge Details
				</div>
				<div class="message">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<s:if test="%{deleteMessage=='true'}"> deleted successfully</s:if>
				</div>
				<div class="clear tabledata">
					<table>
						<tr>
							<th width="10%">S.No</th>
							<th width="30%">Product Name</th>
							<th width="20%">Unit Rate</th>
							<th width="10%">Benefit</th>
							<th width="10%">Validity</th>
							<th width="10%">Group</th>
							<th width="10%">&nbsp;</th>
						</tr>
						<s:iterator value="productList" status="stat">
							<tr>
								<td><s:property value="#stat.count" /></td>
								<td><s:property value="productName" /></td>
								<td><s:property value="productUnitRate" /></td>
								<td><s:property value="productBenefit" /></td>
								<td><s:property value="productValidity" /></td>
								<td><s:property value="productGoup" /></td>								
								<td><span class="tools icon-pencil"></span> <span class="tools icon-x"></span></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			
		</div>
	</div>
</div>
<sj:submit formIds="productForm" id="productFormSubmit" cssStyle="display:none;" targets="contentDiv" />
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<s:include value="footer.jsp" />