/**
 * 
 */
function sleep(milliseconds) {
	var start = new Date().getTime();
	for (var i = 0; i < 1e7; i++) {
		if ((new Date().getTime() - start) > milliseconds) {
			break;
		}
	}
}

$.subscribe('before', function(event, data) {
	$('#successMsg').hide();
	$('#errorMsg').hide();
});

$.subscribe('complete', function(event, data) {
	var resultData = $(data).text();
	$('#successMsg').hide();
	$('#errorMsg').hide();
	if (resultData.indexOf('Failed') > 0) {
		$('#errorMsg').text(resultData);
		$('#errorMsg').show().fadeOut(1000);

	} else {
		// alert($('#global').val());
		if ($('#global').val() == 'product') { // product list
			loadList();
		} else if ($('#global').val() == 'customer') { // customer list
			$('#loadCustomerList').click();
		} else if ($('#global').val() == 'purchase') {
			loadPurchaseDetails();
		} else if ($('#global').val() == 'sales') {
			loadSalesDetails();
		} else if ($('#global').val() == 'settings') {
			if ($('#subPage').length > 0) {
				ajaxCall($('#subPage').val() + '.action');
			}
		} else if ($('#global').val() == 'easyRecharge') {
			loadEasyRechargeDetails();
			$('#availBalance').val($('#easyRechargeBalance').val());
			$('#availCash').text($('#easyRechargeBalance').val());
		}
		document.forms[0].reset();
		$('#successMsg').text(resultData);
		$('#successMsg').show().fadeOut(1000);
	}
	event.preventDefault();
});

$.subscribe('errorState', function(event, data) {
	$('#errorMsg').text('Somethinbg went wrong...');
	$('#errorMsg').show().fadeOut(1500);
	event.preventDefault();
});

function confirmDelete(id) {
	$('#deleteId').val(id);
	$('#deleteConfirm').dialog('open');
}

function deleteOk() {
	$('#deleteId_' + $('#deleteId').val()).click();
	$('#deleteConfirm').dialog('close');
}

function deleteNo() {
	$('#deleteConfirm').dialog('close');
}

function printPage() {
	window.print();
}