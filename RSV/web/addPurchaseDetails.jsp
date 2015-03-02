<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	function registerPurchaseDetails() {
		var invoiceNo = $('#invoiceNo').val();
		var invoiceDate = $("#invoiceDate").val();
		var companyName = $('#companyName').val();
		$('#errorMsg').text('');
		if (invoiceNo == '' || invoiceDate == '' || companyName == '') {
			$('#errorMsg').text('All fields are mandatory');
			$('#errorMsg').show().fadeOut('slow');
			return false;
		}

		$("#purchaseDetailsFormSubmit").click();
	}

	function calculateQuantity(toObj) {

		var row = $(toObj).parent().parent().find('td').html();
		var rowindex = parseInt(row) - 1;
		var findHtml = 'td input[name = "purchaseEntry.purchaseDetailsList['
				+ rowindex + '].serialNoFrom"]';
		var quantityElementId = '#quantity_' + rowindex;

		var toValue = $(toObj).val();
		var fromValue = $(toObj).parent().parent().find(findHtml).val();

		$('#errorMsg').text('');

		if (parseInt(fromValue) > parseInt(toValue)) {
			$('#errorMsg').text(
					'Serial Number To value should be greater than From value');
			$('#errorMsg').show('slow');
			return false;
		}
		$('#errorMsg').hide('slow');
		var quantityValue = (toValue - fromValue) + 1;

		$(quantityElementId).attr('value', quantityValue);
	}

	function calculateAmount(unitRateObj) {
		//alert()
		var row = $(unitRateObj).parent().parent().find('td').html();
		var rowindex = parseInt(row) - 1;
		var findHtml = 'td input[name = "purchaseEntry.purchaseDetailsList['
				+ rowindex + '].purchseQuantity"]';
		var amountElementId = '#amount_' + rowindex;
		var quantityValue = $(unitRateObj).parent().parent().find(findHtml)
				.val();
		var unitRateValue = $(unitRateObj).val();

		var amountValue = quantityValue * unitRateValue;

		$(amountElementId).attr('value', amountValue.toFixed(2));

	}

	function calculateTotal(flag) {
		var rowCount = parseInt($('#rowCount').val());
		if (1 === flag) { // flag 1 for total amount
			var totalAmount = 0.0;
			var amount = 0;
			for (var i = 0; i <= rowCount; i++) {
				amount = $.trim($('#amount_' + i).val());
				if (!isNaN(amount))
					totalAmount += parseFloat(amount);
			}
			$('#totalAmount').text(totalAmount.toFixed(2));
			$('#totalAmount_hdn').val(totalAmount.toFixed(2));
		} else if (2 === flag) { // flag 1 for quantity
			var totalQuantity = 0;
			for (var i = 0; i <= rowCount; i++) {
				var quantity = $.trim($('#quantity_' + i).val());
				if (!isNaN(quantity))
					totalQuantity += parseInt($('#quantity_' + i).val());
			}
			// 			$('#totalQuantity').text(totalQuantity);
			$('#totalQuantity_hdn').val(totalQuantity);
		}
	}

	function addRow() {
		console.log($('#productId_0').html());
		var rowCount = parseInt($('#rowCount').val());
		rowCount += 1;
		var rowHtml = '<tr id="purchaseEntryTr_' + rowCount + '">'
				+ '<td>'
				+ (rowCount + 1)
				+ '</td>'
				+ '<td> <select name="purchaseEntry.purchaseDetailsList['+rowCount+'].productId" id="productId_'+rowCount+'">'
				+ $('#productId_0').html()
				+ ' </select></td>'
				+ '<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" value="0" id="" name="purchaseEntry.purchaseDetailsList['+rowCount+'].serialNoFrom" /></td>'
				+ '<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" value="0" onblur="calculateQuantity(this);" id="" name="purchaseEntry.purchaseDetailsList['
				+ rowCount
				+ '].serialNoTo" /></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" readonly="true" value="0" onblur="calculateTotal(2)" id="quantity_'
				+ rowCount
				+ '" name="purchaseEntry.purchaseDetailsList['
				+ rowCount
				+ '].purchseQuantity" /></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" onblur="calculateAmount(this);calculateTotal(1);" value="0.0" name="purchaseEntry.purchaseDetailsList['
				+ rowCount
				+ '].unitRate" /></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" readonly="true" id="amount_'
				+ rowCount
				+ '" onblur="" value="0" name="purchaseEntry.purchaseDetailsList['
				+ rowCount + '].amount" /></td>'
				+ '</tr>';
		$('#purchaseEntryTable').append(rowHtml);
		$('#rowCount').val(rowCount);
	}
	function delRow() {
		var rowCount = parseInt($('#rowCount').val());
		if (rowCount > 0 && $('#purchaseEntryTr_' + rowCount).length) {
			$('#purchaseEntryTr_' + rowCount).remove();
			$('#rowCount').val(rowCount - 1);
		}
		calculateTotal(1);
		calculateTotal(2);
	}
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Purchase Entry</h2>
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
				<!-- 				<div class="create"> -->
				<%-- 					<s:a href="#" onclick="addPurchase()" cssClass="button button-style2 button-mini butcurve-sml">Purchase Entry</s:a> --%>
				<!-- 				</div> -->
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- Purchase Entry
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead left-orange">
						<h2>Purchase Entry</h2>
					</div>
				</div>
				<!-- Right side table & Form -->
				<s:url action="registration" var="create" />
				<div class="clear tabledata">
					<table style="">
						<s:iterator value="purchaseEntryList" var="entryList" status="statEntry" begin="0" end="0">
							<tr>
								<%-- 								<th colspan="5"><s:if test="%{purchaseId!=null && purchaseId!=0}">Edit</s:if> <s:else>Add</s:else> Purchase Entry</th> --%>
							</tr>
							<tr>
								<td colspan="3"><s:form id="PurchaseDetailsForm" name="PurchaseDetailsForm" method="POST" theme="simple" action="purchaseRegister">
										<s:hidden name="purchaseEntry.purchaseId" id="pid" value="%{purchaseId}" />
										<div class="formdata"> 
											<div class="formBox box-shodow top-orange">
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
														<label>Invoice No</label>
													</p>
													<span><s:textfield theme="simple" size="20" id="invoiceNo" name="purchaseEntry.invoiceNo" value="%{invoiceNo}" /></span>
												</div>
												<div class="top-bottom">
													<p>
														<label>Invoice Date</label>
													</p>
													<sj:datepicker showOn="focus" cssClass="ps_text_1" cssStyle="width:20%;" id="invoiceDate" name="purchaseEntry.invoiceDate" value="%{invoiceDate}" readonly="true" changeMonth="true"
														changeYear="true" maxDate="0" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Company Name</label>
													</p>
													<s:select list="companyList" id="companyName" theme="simple" listKey="companyId" listValue="companyName" name="purchaseEntry.companyId" value="companyId" />
													<%-- 								<s:textfield theme="simple" size="20" id="fname" name="purchaseEntry.companyName" /> --%>
												</div>
												<div class="top-bottom"></div>
											</div>
										</div>
										<!-- 										<div class="top-bottom clear" style="height: 30px;"></div> -->
										<div class="padd-20">
											<div class="box-shodow-all padd-10">
												<table id="purchaseEntryTable">
													<tr>
														<th rowspan="2" width="5%">S.NO</th>
														<th rowspan="2" width="15%">PRODUCT</th>
														<th colspan="2" width="54%">SERIAL NO</th>
														<th rowspan="2" width="8%">QUANTITY</th>
														<th rowspan="2" width="8%">UNIT RATE</th>
														<th rowspan="2" width="10%">AMOUNT</th>
													</tr>
													<tr>
														<th width="8%">From</th>
														<th width="8%">To</th>
													</tr>
													<s:set var="rowCount" value="0" />
													<s:iterator value="purchaseDetailsList" var="lst" status="stats">
														<s:hidden name="purchaseEntry.purchaseDetailsList[%{#stats.index}].purchaseDetailId" id="pdid" value="%{purchaseDetailId}" />
														<tr id="purchaseEntryTr_<s:property value="#stats.index"/>">
															<td><s:property value="#stats.count" /></td>
															<td><s:select list="productList" listKey="productId" listValue="productName" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].productId" id="productId_%{#stats.index}"
																	value="productId" /></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" id="" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].serialNoFrom" value="%{serialNoFrom}" /></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" id="" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].serialNoTo" value="%{serialNoTo}"
																	onblur='calculateQuantity(this);' /></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" readonly="true" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].purchseQuantity" value="%{purchseQuantity}"
																	onblur="calculateTotal(2);" id="quantity_%{#stats.index}" /></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].unitRate" value="%{unitRate}"
																	onblur="calculateAmount(this); calculateTotal(1);" /></td>
															<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" readonly="true" name="purchaseEntry.purchaseDetailsList[%{#stats.index}].amount" value="%{amount}" onblur=""
																	id="amount_%{#stats.index}" /></td>
														</tr>
														<s:set var="rowCount" value="%{#stats.index}" />
													</s:iterator>

												</table>
												<br> <br>
												<div id="totalDiv">
													<div>
														<a onclick="addRow();" class="btn b-green"> <span class="fa-plus"></span></a> &nbsp; <a class="btn b-red" onclick="delRow();"> <span class="fa-minus"></span></a> <span
															style="padding-left: 60%;">Total Purchase Amount</span>
													</div>
													<div class="totalFont c-green">
														<span class="fa-inr" id="totalAmount"> <s:property value="%{totalAmount}" />
														</span>
														<%-- 														 <span class="fa-inr"></span> --%>
													</div>
													<!-- 													 <div></div> -->
													<s:hidden theme="simple" id="totalQuantity_hdn" readonly="true" name="purchaseEntry.totalQuantity" value="%{totalQuantity}" />
													<s:hidden theme="simple" id="totalAmount_hdn" readonly="true" name="purchaseEntry.totalAmount" value="%{totalAmount}" />
												</div>
												<!-- 												<table > -->
												<!-- 													<tr> -->
												<%-- 														<td colspan="2" width="54%"><a onclick="addRow();" class="btn b-green"> <span class="fa-plus"></span></a> &nbsp; <a class="btn b-red" onclick="delRow();"> <span class="fa-minus"></span></a> --%>
												<%-- 															<span style="padding-left: 70%;">Total</span></td> --%>
												<%-- 												<td width="13%"><span id="totalQuantity"><s:property value="purchaseEntry.totalQuantity" /> </span> <s:hidden theme="simple" id="totalQuantity_hdn" readonly="true"<%-- 																name="purchaseEntry.totalQuantity" value="%{totalQuantity}" /></td> --%>
												<%-- 														<td width="13%">&nbsp;</td> --%>
												<%-- 														<td width="13%"><span id="totalAmount"><s:property value="purchaseEntry.totalAmount" /> </span> <s:hidden theme="simple" id="totalAmount_hdn" readonly="true" --%>
												<%-- 																name="purchaseEntry.totalAmount" value="%{totalAmount}" /></td> --%>

												<!-- 													</tr> -->
												<!-- 												</table> -->
												<div class="formdata">
													<div class="top-bottom" align="right" style="padding-right: 25px;">
														<s:reset cssClass="button-style1 button-small1 butcurve-sml" theme="simple" value="Cancel" label="Cancel" />
														<input type="button" class="button-style2 button-small1 butcurve-sml submit" value="Submit" onclick="registerPurchaseDetails();" />
													</div>
												</div>

											</div>
										</div>
									</s:form> <input type="hidden" id="rowCount" name="rowCount" value='<s:property value="#rowCount"/>'></td>
							</tr>
						</s:iterator>
					</table>

				</div>
				<!-- Right side table & Form -->

			</div>
		</div>
	</div>
</div>
<sj:submit formIds="PurchaseDetailsForm" id="purchaseDetailsFormSubmit" cssStyle="display:none;" />
<!-- targets="contentDiv" -->
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<script>
	$('#invoiceNo').prop('autocomplete', 'off');
</script>
<s:include value="footer.jsp" />