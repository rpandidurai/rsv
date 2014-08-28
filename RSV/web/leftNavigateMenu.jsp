<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<div  class="navigation" style="display: none;">
	<ul id="menuSelector" >
		<li class=""><div class=""></div> <s:a theme="simple" href="#" onclick="showMenu()" onmouseover="showMenu()">Show Menu</s:a></li>
	</ul>
	<ul id="leftMenu" style="display: none;">
<%-- 		<li class=""><div class=""></div> <s:a theme="simple" href="#" onclick="showMenu()">Hide Menu</s:a></li> --%>
		<li><div class="icon-user"></div> <s:a theme="simple" href="product.action">Product</s:a></li>
		<li><div class="icon-user"></div> <s:a theme="simple" href="customer.action">Customer</s:a></li>
		<li><div class="icon-file"></div> <s:a theme="simple" href="purchase.action">Purchase Book</s:a></li>
		<li><div class="icon-file"></div> <s:a theme="simple" href="addPurchase.action">Purchase Entry</s:a></li>
		<li><div class="icon-file"></div> <s:a theme="simple" href="sales.action">Sales Book</s:a></li>
		<li><div class="icon-file"></div> <s:a theme="simple" href="addSales.action">Sales Entry</s:a></li>
		<li><div class="icon-file"></div> <s:a theme="simple" href="recharge.action">Recharge Details</s:a></li>
	</ul>
	<!-- 	<div class="leftshadow"> -->
	<!-- 		<img src="images/leftnavbox-shadowleft.jpg" /> -->
	<!-- 	</div> -->
	<!-- 	<div class="rightshadow"> -->
	<!-- 		<img src="images/leftnavbox-shadowright.jpg" /> -->
	<!-- 	</div> -->
</div>
<!-- <div id="menuSelector" class="navigation" style="display: none;"></div> -->