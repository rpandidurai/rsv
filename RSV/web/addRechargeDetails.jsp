<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function loadPurchaseDetails() {
		$('#filterDateSubmit').click();
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Recharge</h2>
		</div>
		<!-- Right side table & Form -->
		<div id="contentDiv">
			<div class="rightsidecont right">
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- Recharge
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead">
						<h2>Recharge Entry</h2>
					</div>
				</div>
				<div class="clear tabledata">
					<table>
						<!-- 						<tr> -->
						<%-- 							<th><s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> <s:else>Add</s:else> Customer</th> --%>
						<!-- 						</tr> -->
						<tr>
							<td colspan="3"><s:form id="rechargeDetailsForm" name="rechargeDetailsForm" method="POST" theme="simple" action="rechargeRegister">
									<%-- 								<s:hidden name="user.id" id="uid" /> --%>
									<div class="formdata">
										<div class="formBoxFlat top-green">
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
													<label>Recharge Name</label>
												</p>
												<s:select list="productList" listKey="productId" listValue="productName" headerValue="Select" headerKey="0"/>
											</div>
											<div class="top-bottom">
												<p>
													<label>Stock Serial No</label>
												</p>
												<s:textfield theme="simple" size="20" id="fname" name="customer.customerName" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Delivery Boy</label>
												</p>
												<s:textfield theme="simple" size="20" id="fname" name="customer.customerName" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Recharge Name</label>
												</p>
												<s:textfield theme="simple" size="20" id="fname" name="customer.customerName" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Recharge Name</label>
												</p>
												<s:textfield theme="simple" size="20" id="fname" name="customer.customerName" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Area</label>
												</p>
												<s:textfield theme="simple" size="20" id="lname" name="customer.customerArea" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Address</label>
												</p>
												<s:textfield theme="simple" size="20" id="lname" name="customer.customerAddress" />
											</div>
											<div class="top-bottom">
												<p>
													<label>Phone No</label>
												</p>
												<s:textfield theme="simple" size="20" id="lname" name="customer.customerPhoneNo" />
											</div>
											<div class="top-bottom">
												<input type="button" class="button-style2 button-small1 butcurve-sml submit" style="width: 80%; height: 40px;" value="Submit" onclick="registerCustomer();" />
											</div>
											<div class="clear">
												<s:reset cssClass="button-style1 button-small1 butcurve-sml" theme="simple" cssStyle="width:80%;height: 40px;" value="Cancel" label="Cancel" />
											</div>
											<div class="top-bottom"></div>
										</div>
									</div>
								</s:form></td>
						</tr>
					</table>

				</div>
			</div>
			<!-- Right side table & Form -->
		</div>
	</div>
	<s:hidden id="global" value="recharge" />
	<s:hidden id="deleteId" />
</div>
<!-- content area -->
<s:include value="footer.jsp" />