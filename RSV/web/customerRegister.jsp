<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<script>
	function registerCustomer() {
		var name = $('#fname').val();
		var area = $('#area').val();
		var address = $('#address').val();
		var phoneNo = $('#phoneNo').val();

		if (name == '' || area == '' || address == '' || phoneNo == '') {
			$('#errorMsg').text('All fields are mandatory');
			$('#errorMsg').show();//.fadeOut(1500);

			return false;
		}

		$('#errorMsg').hide();
		$("#customerFormSubmit").click();
	}

	$(document).ready(function() {

		$(".phoneNo").keydown(function(event) {
			eventKeyDown(event);
		});

	});

	function eventKeyDown(event) {

		var num = event.keyCode;
		if ((num > 95 && num < 106) || (num > 36 && num < 41) || num == 9
				|| num == 116) {
			return;
		}
		if (event.shiftKey || event.ctrlKey || event.altKey) {
			$("#errorMsg").html("Digits Only").show().fadeOut("slow");
			event.preventDefault();
		} else if (num != 46 && num != 8) {
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
			<h2>Customer</h2>
		</div>
		<!-- Left navigation -->
		<div class="leftnavigation left">
			<s:include value="leftNavigateMenu.jsp" />
		</div>
		<!-- Left navigation -->

		<!-- Right side table & Form -->
		<div id="contentDiv">
			<!-- Right side table & Form -->
			<s:url action="registration" var="create" />
			<div class="rightsidecont right">
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- <a href="customer.action">Customer</a>-
					<%-- 		<s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> --%>
					<%-- 		<s:else>Create</s:else> --%>
					Add New Customer
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead left-green c-green">
						<h2>Add / Update Customer</h2>
					</div>
				</div>
				<div class="clear tabledata">
					<span class="fa-reply pointer c-skyblue green"></span>
					<table>
						<tr>
							<%-- 							<th><b class="c-green"><s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> <s:else>Add</s:else> Customer</b></th> --%>
						</tr>
						<tr>
							<td colspan="3"><s:form id="customerForm" name="customerForm" method="POST" theme="simple" action="customerRegister">
									<s:iterator value="customerList" var="itr" begin="0" end="0" status="stats">
										<s:hidden name="customer.customerId" value="%{customerId}" />
										<div class="formdata">

											<div class="formBox box-shodow top-green">
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
														<label>Customer Name</label>
													</p>
													<s:textfield theme="simple" size="20" id="fname" name="customer.customerName" value="%{customerName}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Area</label>
													</p>
													<s:textfield theme="simple" size="20" id="area" name="customer.customerArea" value="%{customerArea}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Address</label>
													</p>
													<s:textfield theme="simple" size="20" id="address" name="customer.customerAddress" value="%{customerAddress}" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Phone No</label>
													</p>
													<s:textfield theme="simple" size="20" id="phoneNo" cssClass="phoneNo" name="customer.customerPhoneNo" value="%{customerPhoneNo}" />
												</div>
												<div class="btn-row">
<%-- 													<s:a href="customer.action"> --%>
<!-- 														<input type="button" class="button-style2 button-small1 butcurve-sml btn-red" value="Customer List" /> -->
<%-- 													</s:a> --%>
<%-- 													<s:reset cssClass="button-style1 button-small1 butcurve-sml ps-btn" theme="simple" value="Cancel" label="Cancel" /> --%>
													<input type="button" class="button-style2 button-small1 butcurve-sml submit ps-btn" value="Submit" onclick="registerCustomer();" />
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
		</div>
	</div>
</div>
<sj:submit formIds="customerForm" id="customerFormSubmit" cssStyle="display:none;" />
<!-- content area -->
<s:include value="footer.jsp" />