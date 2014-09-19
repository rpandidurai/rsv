<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
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

	function getProductFareStock(rowValue) {
		var productId = $('#product_'+rowValue).val();
		if(productId == '0'){
			$('#stock_'+rowValue).text('-');
            $('#stock_'+rowValue).attr('class','bold');
            $('#quantity_'+rowValue).prop('readonly', true);
			return false;
		}
		var rowCount = parseInt($('#rowCount').val());
		var isAlreadyAdded = false;
		for (var i = 0; i <= rowCount; i++) {
			if(i == rowValue)
				continue;
            var otherId = $('#product_'+i).val();
            if (productId == otherId){               
                isAlreadyAdded = true;
                break;
            }
        }
		if(isAlreadyAdded){
			 alert('Product already added, select another product');
			 $('#product_'+rowValue).val(0)
			 return false;
		}
		$.ajax({
			url : "getProductFareStock.action",
			dataType : 'text',
			data: {filterId:productId},
			type : 'POST',
			async : true,
			success : function(res) {
				//alert(res.split(',')[0]+' -- '+res.split(',')[1]);
				if(res.split(',')[1] != ''){
				    $('#stock_'+rowValue).text(res.split(',')[1]);
				    $('#stock_'+rowValue).attr('class', 'badge bg-info');
				    $('#quantity_'+rowValue).prop('readonly', false);
				    $('#quantity_'+rowValue).val(0);
				} else { 
					$('#stock_'+rowValue).text(0);
					$('#stock_'+rowValue).attr('class','badge bg-important');
					$('#quantity_'+rowValue).prop('readonly', true);
				}
				if(res.split(',')[0] != ''){
				    $('#unitRate_'+rowValue).val(res.split(',')[0]);
				} else {
					$('#unitRate_'+rowValue).val(0);
				}
			}
		});
	}
	function showAlert(message){
	        new $.flavr({
	            content : message,
	            buttons : {	                
	                OK : function() {
	                }
	            },
// 	            buttonDisplay : 'stacked',
	            closeOverlay : true,
	            closeEsc : true
	        });
	}
	function onSubmitValidation(){
		var rowCount = parseInt($('#rowCount').val());        
		var isFilled = false;
		for (var i = 0; i <= rowCount; i++) {
            var quantity = $.trim($('#quantity_' + i).val());
            if (quantity == '0'){
            	$('#quantity_'+i+'').css('border', '2px solid red');
            	isFilled = true;
            	break;
            }            	
        }
		if(isFilled){
			//showAlert('Fill All the details');
			$(document).scrollTop(0);
			 $("#errorMsg").text("Fill All the details").show().fadeOut("slow");
			 return false;
		}
		
		return true;
	}
    function calculateSales(rowValue){
    	$('#quantity_'+rowValue).css('border', '0');
    	var quantity = $.trim($('#quantity_'+rowValue).val());
        if(quantity == ''){
        	$('#quantity_'+rowValue).val(0);
        	return false;
        }
    	var rowCount = parseInt($('#rowCount').val());    	
    	var stock = parseInt($('#stock_'+rowValue).text());
    	quantity = parseInt(quantity);
    	if(quantity > stock){
    		alert('Entered product quantity is more than stock');
    		$('#quantity_'+rowValue).val(0);
    		return false;
    	}    	
    	var unitRate = parseFloat($('#unitRate_'+rowValue).val());
    	$('#amount_'+rowValue).val((quantity * unitRate).toFixed(2));
    	
    	var totalQuantity = 0;
        for (var i = 0; i <= rowCount; i++) {
            var quantity = $.trim($('#quantity_' + i).val());
            if (!isNaN(quantity))
                totalQuantity += parseInt($('#quantity_' + i).val());
        }
        $('#totalQuantity').val(totalQuantity);
        
    	var totalAmount = 0.0;
        for (var i = 0; i <= rowCount; i++) {
            var amount = $.trim($('#amount_' + i).val());
            if (!isNaN(amount))
                totalAmount += parseFloat(amount);
        }
        $('#totalAmount').val(totalAmount.toFixed(2));
    	
    	return true;
    	
        }
    
	function registerSalesDetails() {
		if(!onSubmitValidation()){
			return false;
		}
		$("#salesDetailsFormSubmit").click();
		
	}

	function calculateTotal(flag) {
		var rowCount = parseInt($('#rowCount').val());
		if (1 === flag) {
			var totalAmount = 0.0;
			for (var i = 0; i <= rowCount; i++) {
				var amount = $.trim($('#amount_' + i).val());
				if (!isNaN(amount))
					totalAmount += parseFloat(amount);
			}
			$('#totalAmount').val(totalAmount.toFixed(2));
		} else if (2 === flag) {
			var totalQuantity = 0;
			for (var i = 0; i <= rowCount; i++) {
				var quantity = $.trim($('#quantity_' + i).val());
				if (!isNaN(quantity))
					totalQuantity += parseInt($('#quantity_' + i).val());
			}
			$('#totalQuantity').val(totalQuantity);
		}

	}

	function addRow() {
		var rowCount = parseInt($('#rowCount').val());
		if($('#product_'+rowCount).val() == 0 || $('#quantity_'+rowCount).val() == 0){
			alert('Add all the details for last product before adding another product');
			return false;
		}
		rowCount += 1;
		var rowHtml = '<tr id="salesEntryTr_' + rowCount + '">'
				+ '<td>'
				+ (rowCount + 1)
				+ '</td>'
				+ '<td><select style="width:60%;" id="product_'+rowCount+'" name="salesEntry.salesDetailsList['
				+ rowCount
				+ '].productId" onchange="getProductFareStock('+rowCount+');" >'
				+ $('#product_0').html()
				+ '</select><span>In Stock <br><span id="stock_'+rowCount+'" class="bold">-</span></span></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1 digits" onblur="calculateSales('+rowCount+')" id="quantity_'
				+ rowCount
				+ '" name="salesEntry.salesDetailsList['
				+ rowCount
				+ '].quantity" value="0" /></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" onblur="calculateSales('+rowCount+')" name="salesEntry.salesDetailsList['+rowCount+'].unitRate" id="unitRate_'+rowCount+'" value="0" readonly="false" /></td>'
				+ '<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" id="amount_'
				+ rowCount
				+ '" onblur="calculateTotal(1)" name="salesEntry.salesDetailsList['
				+ rowCount + '].amount" value="0" readonly="true" /></td>'
				//+ '<td><s:a ><span class="tools icon-x red"></span></s:a></td>'
				+ '</tr>';
		$('#salesEntryTable').append(rowHtml);
		$('#rowCount').val(rowCount);
	}
	function delRow() {
		var rowCount = parseInt($('#rowCount').val());
		if (rowCount > 0 && $('#salesEntryTr_' + rowCount).length) {
			$('#salesEntryTr_' + rowCount).remove();
			$('#rowCount').val(rowCount - 1);
		}
		calculateTotal(1);
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
			<s:url action="registration" var="create" />
			<div class="rightsidecont right">
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- <a href="customer.action">Sales Details</a>-
					<%-- 		<s:if test="%{user.id!=null && user.id!=0}">Edit</s:if> --%>
					<%-- 		<s:else>Create</s:else> --%>
					Sales Details Entry
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead">
						<h2>Sales Entry</h2>
					</div>
				</div>
				<div class="clear tabledata">
					<table>
						<s:iterator value="salesEntryList" var="itr" status="sEstats" begin="0" end="0">
							<!-- 							<tr> -->
							<%-- 								<th colspan="5"><s:if test="%{salesEntryId!=null && salesEntryId!=0}">Edit</s:if> <s:else>Add</s:else> Sales Details</th> --%>
							<!-- 							</tr> -->
							<tr>
								<td colspan="3"><s:form id="SalesDetailsForm" name="SalesDetailsForm" method="POST" theme="simple" action="salesRegister">

										<s:hidden name="salesEntry.salesEntryId" id="salesEntryId" value="%{salesEntryId}" />
										<div class="formdata">
											<div class="formBox box-shodow top-red">
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
													<span class="success fa-remove-sign" id="successMsg" style="display: none;"></span> <span class="failed fa-remove-sign" id="errorMsg" style="display: none;"></span>
												</div>
												<div class="top-bottom">
													<p>
														<label>Bill No</label>
													</p>
													<span><s:textfield theme="simple" size="20" id="fname" name="salesEntry.billNo" readonly="true" value="%{billNo}" /> </span>
												</div>
												<div class="top-bottom">
													<p>
														<label>Date</label>
													</p>
													<sj:datepicker showOn="focus" cssClass="ps_text_1" cssStyle="width:20%;" name="salesEntry.entryDate" value="%{entryDate}" readonly="true" changeMonth="true" changeYear="true" maxDate="0" />
												</div>
												<div class="top-bottom">
													<p>
														<label>Customer Name</label>
													</p>
													<s:select list="customerList" listKey="customerId" listValue="customerName" name="salesEntry.customerId" value="customerId"></s:select>
												</div>
												<div class="top-bottom">
													<p>
														<label>Delivery Boy</label>
													</p>
													<s:select list="deliveryBoysList" listKey="deliveryId" listValue="deliveryName" name="salesEntry.deliveryId" value="deliveryId"></s:select>
												</div>
												<div class="top-bottom"></div>
											</div>
										</div>
										<div class="top-bottom clear" style="height: 30px;"></div>
										<div class="padd-20">
											<div class="box-shodow-all padd-10">
												<table id="salesEntryTable">
													<tr>
														<th width="8%">S.NO</th>
														<th width="20%">PRODUCT</th>
														<th width="15%">QUANTITY</th>
														<th width="15%">UNIT RATE</th>
														<th width="15%">AMOUNT</th>
														<!-- 												<th width="10%">ACTION</th> -->
													</tr>
													<s:set var="rowCount" value="0" />
													<s:iterator value="salesDetailsList" var="salesDetailsItr" status="stats">
														<s:hidden name="salesEntry.salesDetailsList[%{#stats.index}].salesId" id="sdid" value="%{salesId}" />
														<tr id='salesEntryTr_<s:property value="#stats.index" />'>
															<td><s:property value="#stats.count" /></td>
															<td><s:select cssStyle="width:60%;" list="productList" listKey="productId" listValue="productName" id="product_%{#stats.index}"
																	name="salesEntry.salesDetailsList[%{#stats.index}].productId" headerKey="0" headerValue="Select" value="productId" onchange="getProductFareStock(%{#stats.index});" /> <span>In
																	Stock <br> <span id='stock_<s:property value="#stats.index" />' class="bold">-</span>
															</span></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1 digits" onblur="calculateSales(%{#stats.index});" id="quantity_%{#stats.index}"
																	name="salesEntry.salesDetailsList[%{#stats.index}].quantity" value="%{quantity}" /></td>
															<td><s:textfield theme="simple" size="10" cssClass="ps_text_1" name="salesEntry.salesDetailsList[%{#stats.index}].unitRate" id="unitRate_%{#stats.index}" value="%{unitRate}"
																	onblur="calculateSales(%{#stats.index});" readonly="false" /></td>
															<td><s:textfield theme="simple" size="20" cssClass="ps_text_1" onblur="calculateTotal(1);" id="amount_%{#stats.index}" name="salesEntry.salesDetailsList[%{#stats.index}].amount"
																	readonly="true" value="%{amount}" /></td>
															<%-- 													<td><s:a> --%>
															<%-- 															<span class="tools icon-x red"></span> --%>
															<%-- 														</s:a></td> --%>
														</tr>
														<s:set var="rowCount" value="%{#stats.index}" />
													</s:iterator>
												</table>
												<table id="salesTotalTble">
													<tr>
														<td colspan="2" width="43%"><a onclick="addRow();" class="btn b-green"> <span class="fa-plus"></span></a> &nbsp; <a class="btn b-red" onclick="delRow();"> <span class="fa-minus"></span></a><span
															style="padding-left: 70%;">Total</span></td>
														<td width="15%"><s:textfield theme="simple" size="30" cssClass="ps_text_1" id="totalQuantity" readonly="true" name="salesEntry.totalQuantity" value="%{totalQuantity}" /></td>
														<!-- 														<td width="15%">&nbsp;</td> -->
														<td width="15%"><s:textfield theme="simple" size="20" cssClass="ps_text_1" id="totalAmount" readonly="true" name="salesEntry.totalAmount" value="%{totalAmount}" /></td>

													</tr>
												</table>


												<div class="formdata">
													<div class="top-bottom" align="right" style="padding-right: 25px;">
														<s:reset cssClass="button-style1 button-small1 butcurve-sml" theme="simple" value="Cancel" label="Cancel" />
														<input type="button" class="button-style2 button-small1 butcurve-sml submit" value="Submit" onclick="registerSalesDetails();" />
													</div>
												</div>
											</div>
										</div>
									</s:form> <input type="hidden" id="rowCount" name="rowCount" value='<s:property value="#rowCount"/>'></td>
							</tr>
						</s:iterator>
					</table>
				</div>
			</div>
			<!-- Right side table & Form -->
		</div>
	</div>
</div>
<sj:submit formIds="SalesDetailsForm" id="salesDetailsFormSubmit" cssStyle="display:none;" />
<!-- content area -->
<script>
$(document).ready(function() {
    $(".digits").keydown(function(event) {
        eventKeyDown(event);
    });
});
</script>
<s:include value="footer.jsp" />