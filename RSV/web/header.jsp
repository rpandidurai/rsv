<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<sj:head jqueryui="true" />
<title><s:text name="appn.title" /> - Home</title>
<link rel="stylesheet" href="css/theAppStyle.css" type="text/css" />
<link rel="stylesheet" href="css/style.css" type="text/css" />
<link rel="stylesheet" href="css/menu.css" type="text/css" />
<link rel="stylesheet" href="css/psStyle.css" type="text/css" />

<!-- <link rel="stylesheet" href="css/demo.css" type="text/css" /> -->
<!-- <link rel="stylesheet" href="css/docs.css" type="text/css" /> -->
<link rel="stylesheet" href="css/print.css" media="print" type="text/css" />

<link rel="stylesheet" type="text/css" href="css/animate.css" />
<link rel="stylesheet" type="text/css" href="css/flavr.css" />

<script type="text/javascript" src="js/flavr.js"></script>
</head>
<style>
ul.errorMessage li {
	margin-top: 5px;
}

.black_overlay {
	display: none;
	position: fixed;
	top: 0;
	left: 0;
	bottom: 0;
	right: 0;
	background-color: black;
	z-index: 59;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 80);
	right: 0;
}

.white_content {
	display: none;
	position: fixed;
	top: 10%;
	left: 15%;
	width: 70%;
	height: 80%;
	padding: 4px;
	border: 0px solid #000;
	background-color: transparent;
	z-index: 60;
	overflow: auto;
}
</style>
<script type="text/javascript" src="js/rsv.js"></script>

<script>
	
</script>
</head>
<body>
	<div id="fade" class="black_overlay no-print"></div>
	<div class="header no-print">
		<div class="top">&nbsp;</div>
		<div class="innercontainer">
			<div class="left logo ">
				<h5>
					<span class="icon-network"> &nbsp;<span>RSV Traders</span>
					</span>
				</h5>
			</div>
			<div class="right icons">
				<div class="logout">
					<s:if test="%{#session.get('USER') != null}">
											Welcome <span><s:text name="session.userName" /></span>
						<br />
						<s:a cssClass="fa-power-off c-red" href="logout.action">logout</s:a>
					</s:if>
					<br /> <span id="time"></span>
				</div>
			</div>
		</div>
	</div>
	<script>
		show_datetime();
		setInterval("show_datetime()", 1000);
		function show_datetime() {
			var curtime = new Date();
			var curhour = curtime.getHours();
			var curmin = curtime.getMinutes();
			var cursec = curtime.getSeconds();
			var time = "";
			if (curhour == 0)
				curhour = 12;
			time = (curhour > 12 ? curhour - 12 : curhour) + ":"
					+ (curmin < 10 ? "0" : "") + curmin + ":"
					+ (cursec < 10 ? "0" : "") + cursec + " "
					+ (curhour >= 12 ? "PM" : "AM");
			try {
				document.getElementById("time").innerHTML = curtime
						.toLocaleDateString()
						+ ", " + time;
			} catch (e) {
			}
		}
	
		
	</script>