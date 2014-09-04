<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<script>
	function registerProduct() {
		$('.successMsg').hide();
		var productName = $('#pname').val();
		var unitRate = $('#unit_rate').val();
		var validity = $('#validity').val();
		var benefits = $('#benefits').val();

		// 		var groupList = $("input[name='product.groupId']:checked").val();
		var groupList = $('#productGroup').val();
		//typeof groupList == 'undefined' 
		if (groupList == '0' || productName == '' || unitRate == ''
				|| benefits == '' || validity == '') {
			$('#errorMsg').show().fadeOut("slow");
			$('#errorMsg').text('All fields are mandatory');
			return false;
		}

		$('#errorMsg').text('');
		$("#productFormSubmit").click();
	}

	$(document).ready(function() {
		$("#unit_rate").keydown(function(event) {
			eventKeyDown(event);
		});

		$("#validity").keydown(function(event) {
			eventKeyDown(event);
		});

	});

	function eventKeyDown(event) {
		var num = event.keyCode;
// 		if ((num >= 65 && num <= 90) || (num != 190 && num >= 186) && num != 32) {
// 			$("#errorMsg").html("Digits Only").show().fadeOut("slow");
// 			event.preventDefault();
// 		}

				if ((num > 95 && num < 106) || (num > 36 && num < 41) || num == 9
						|| num == 116) {
					return;
				}
				if (event.shiftKey || event.ctrlKey || event.altKey) {
					$("#errorMsg").html("Digits Only").show().fadeOut("slow");
					event.preventDefault();
				} else if (num != 46 && num != 8 && num != 110 && num != 190) {
					if (isNaN(parseInt(String.fromCharCode(event.which)))) {
						$("#errorMsg").html("Digits Only").show().fadeOut("slow");
						event.preventDefault();
					}
				}

	}
</script>
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Product</h2>
		</div>
		<!-- Left navigation -->
		<!--        <div class="leftnavigation left"> -->
		<%--            <s:include value="leftNavigateMenu.jsp" /> --%>
		<!--        </div> -->
		<!-- Left navigation -->

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<!-- Right side table & Form -->
			<s:url action="registration" var="create" />
			<div class="rightsidecont right">
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- <a href="product.action">Product</a>-
					<%-- 		<s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> --%>
					<%-- 		<s:else>Create</s:else> --%>
					Add New Product
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead left-skyBlue c-skyBlue">
						<h2>Add / Update Product</h2>
					</div>
				</div>
				<div class="clear tabledata">
					<table>
						<tr>
							<%-- 							<th><s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> <s:else>Add</s:else> Product</th> --%>
						</tr>
						<tr>
							<td colspan="3"><s:form id="productForm" name="productForm" method="POST" theme="simple" action="productRegister">
									<%-- 								<s:hidden name="user.id" id="uid" /> --%>
									<s:iterator value="productList" var="itr" status="stats" end="0">
										<s:hidden name="product.productId" value="%{productId}" />
										<div class="formdata">
											<div class="formBox box-shodow top-skyblue">
												<div id="formMessage" class="formMessage">
													<s:if test="hasActionMessages()">
														<span class="success fa-ok-sign"> <s:iterator value="actionMessages">
																<s:property />
															</s:iterator>
														</span>
													</s:if>
													<s:if test="hasActionErrors()">
														<span class="failed fa-remove-sign"> <s:iterator value="actionErrors">
																<s:property />
															</s:iterator>
														</span>
													</s:if>
													<span class="success fa-ok-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
												</div>
												<div class="top-bottom">
													<p>
														<label>Product Name</label>
													</p>
													<s:textfield theme="simple" size="20" id="pname" name="product.productName" value="%{productName}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Unit Rate</label>
													</p>
													<s:textfield theme="simple" size="20" id="unit_rate" name="product.productUnitRate" value="%{productUnitRate}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Benefits</label>
													</p>
													<s:textarea theme="simple" id="benefits" name="product.productBenefits" value="%{productBenefits}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Validity</label>
													</p>
													<s:textfield theme="simple" size="20" id="validity" name="product.productValidity" value="%{productValidity}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Group</label>
													</p>
													<s:select label="Group" name="product.groupId" list="productGroupList" listKey="groupId" listValue="groupName" headerKey="0" id="productGroup" headerValue="Select" value="groupId" />

												</div>
												<div class="btn-row">
													<s:reset cssClass="button-style1 button-small1 butcurve-sml ps-btn" theme="simple" value="Cancel" label="Cancel" />
													<input type="button" class=" fa-remove-sign button-style2 button-small1 butcurve-sml submit ps-btn b-green " value="Submit" onclick="registerProduct();" />
												</div>
											</div>
										</div>
									</s:iterator>
								</s:form></td>
						</tr>
					</table>

				</div>
			</div>
			<!-- Right side table & Form -->

			<script>
				$("#productForm input:reset").click();
			</script>
		</div>
	</div>
</div>
<sj:submit formIds="productForm" id="productFormSubmit" cssStyle="display:none;" />
<!-- content area -->
<s:include value="footer.jsp" />