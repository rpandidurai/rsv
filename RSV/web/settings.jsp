<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<script>
	
</script>
<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Settings</h2>
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
					<s:a href="#" onclick="addPurchase()" cssClass="button button-style2 button-mini butcurve-sml">App Settings</s:a>
				</div>
				<div class="breadcrumb left">
					<a href="loginAction.action">Home</a>- Settings
				</div>
				<div class="clear pageheadDiv">
					<div class="pagehead">
						<h2>Settings</h2>
					</div>
				</div>

				<div id="detailsDiv"></div>
				<div class="content-container no-print">
					<div class="content">
						<div class="item top-red c-red h-red" onclick="ajaxCall('loadUser.action');">
							<span class="fa-user big big-lineh"></span><br> <span class="bold">User</span>
						</div>
					</div>
					<div class="content">
						<div class="item top-skyblue c-skyblue h-skyblue" onclick="ajaxCall('loadGroup.action');">
							<span class="fa-tags big big-lineh"></span><br> <span class="bold">Product Group</span>
						</div>
					</div>
					<div class="content">
						<div class="item c-green h-green" onclick="ajaxCall('loadCompany.action');">
							<span class="fa-tablet big big-lineh"></span><br> <span class="bold">Company</span>
						</div>
					</div>
					<div class="content">
						<div class="item top-orange c-orange h-orange" onclick="ajaxCall('loadDeliveryBoys.action');">
							<span class="fa-male big big-lineh"></span><br> <span class="bold">Delivery Boys</span>
						</div>
					</div>
				</div>
				<div class="clear tabledata"></div>
			</div>
		</div>
	</div>
	<s:hidden id="global" value="settings" />
	<s:hidden id="deleteId" />
</div>
<script>
	function sleep(milliseconds) {
		var start = new Date().getTime();
		for (var i = 0; i < 1e7; i++) {
			if ((new Date().getTime() - start) > milliseconds) {
				break;
			}
		}
	}
	function ajaxCall(url) {
		$.ajax({
			url : url,
			dataType : 'text',
			type : 'POST',
			async : true,
			success : function(res) {
				$('#popDiv').html('');
				$('#popDiv').html(res);
				$('#fade').show('slow');
				$('#light').show('slow');
			}
		});
	}
	function showPop(flag) {
		// 	$('#userList').hide();
		//     $('#companyList').hide();
		//     $('#productGroupList').hide();

		//     document.getElementById('light').style.display = 'block';
		//     document.getElementById('fade').style.display = 'block';
		$('#fade').show('slow');
		$('#light').show('slow');
		//     $('#'+flag).show();
		console.log(flag);
		$('#popDiv').html($('#' + flag).html());
	}
	function closePop() {
		$('#fade').hide('slow');
		$('#light').hide('slow');
		$('#popDiv').html('');
	}
	function userSettings() {
		new $.flavr({
			content : 'Buttons example',
			buttons : {
				submit : {
					text : 'Submit Now',
					style : 'danger',
					addClass : 'submit-btn',
					action : function($container, $second, $third) {
						alert('Submit Now');
						return false;
					}
				},
				reset : function() {
					// 					alert('ps : Reset and close');
				}
			},
			buttonDisplay : 'stacked',
			closeOverlay : true,
			closeEsc : true
		});
	}

	function formCheck() {
		var cont = $('#contentSubDiv').html();
		var html = '   <div class="form-row">'
				+ '       <input type="text" name="username" ' +
			'       placeholder="Username" />'
				+ '   </div>'
				+ '   <div class="form-row">'
				+ '       <input type="password" name="password" ' +
			'       placeholder="Password" />'
				+ '   </div>'
				+ '   <div class="form-row">'
				+ '       <input type="checkbox" name="remember" ' +
			'       id="check"/>'
				+ '       <label for="check">Remember me</label>' + '   </div>';

		new $.flavr({
			// 			title : 'Form',
			// 			iconPath : 'images/',
			// 			icon : 'email.png',
			// 			content : 'HTML form example',
			// 			dialog : 'form',
			// 			form : {
			content : cont,
			// 				method : 'post'
			// 			},
			onSubmit : function($container, $form) {
				alert($form.serialize());
				return false;
			},
			buttonDisplay : 'inline',
			closeOverlay : true,
			closeEsc : true,
			position : 'top-mid',
			onShow : function() {
				//                 this.resize(500, 500);
			}

		});
	}
	$(document).ready(function() {
		console.log("ready!");

	});
</script>
<%-- <s:debug></s:debug> --%>
<!-- content area -->
<div id="light" class="rightsidecont right white_content popDiv ">
	<div id="popDiv" class="clear tabledata"></div>
</div>
<s:include value="footer.jsp" />