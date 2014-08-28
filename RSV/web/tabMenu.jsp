<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>
<div class="clear menu no-print" id="other">
	<div class="container">
		<div id="cssmenu">
			<ul>

				<li id="liprfProduct" class="has-sub"><a href="#"><span class="fa-mobile-phone"></span><span>Product</span></a>
					<ul>
						<li><s:a theme="simple" cssClass="fa-list" href="product.action">Product List</s:a></li>
						<li class="last"><s:a theme="simple" cssClass="fa-cloud-download" href="addProduct.action">Add Product</s:a></li>
					</ul></li>
				<li id="liprfCustomer" class="has-sub"><a href="#"><span class="fa-group"></span><span>Customer</span></a>
					<ul>
						<li><s:a theme="simple" cssClass="fa-list" href="customer.action">Customer List</s:a></li>
						<li class="last"><s:a theme="simple" cssClass="fa-cloud-download" href="addCustomer.action">Add Customer</s:a></li>
					</ul></li>
				<li id="liprfPurchase" class="has-sub"><a href="#"><span class="fa-briefcase"></span><span>Purchase</span></a>
					<ul>
						<li><s:a theme="simple" cssClass="fa-book" href="purchase.action">Purchase Book</s:a></li>
						<li class="last"><s:a theme="simple" cssClass="fa-pencil" href="addPurchase.action">Purchase Entry</s:a></li>
					</ul></li>
				<li id="liprfSales" class="has-sub"><a href="#"><span class="fa-money"></span><span>Sales</span></a>
					<ul>
						<li><s:a theme="simple" cssClass="fa-book" href="sales.action">Sales Book</s:a></li>
						<li class="last"><s:a theme="simple" cssClass="fa-pencil" href="addSales.action">Sales Entry</s:a></li>
					</ul></li>
				<li id="liprfRecharge" class="has-sub"><s:a href="recharge.action">
						<span class="fa-phone-square"></span>
						<span>Recharge</span>
					</s:a>
					<ul>
						<li><s:a theme="simple" href="recharge.action">Recharge</s:a></li>
						<li class="last"><s:a theme="simple" href="addRecharge.action">Recharge Entrys</s:a></li>
					</ul></li>
				<li id="liprfSettings" class="has-sub"><a href="settings.action"><span class="fa-cog"></span><span>Settings</span></a>
<!-- 					<ul> -->
<%-- 						<li><s:a theme="simple" cssClass="fa-book" href="settings.action">Group</s:a></li> --%>
<%-- 						<li class="last"><s:a theme="simple" cssClass="fa-cloud-download" href="addProduct.action">Company</s:a></li> --%>
<!-- 					</ul> -->
					</li>
			</ul>
			<input type="hidden" name="institution_id" id="institution_id" value="1">
		</div>
	</div>
</div>