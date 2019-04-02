<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="/struts-jquery-tags" prefix="sj"%>
<!DOCTYPE html>

<s:include value="header.jsp" />
<s:include value="tabMenu.jsp" />
<!-- content area -->
<div class="innercontainer">
	<div class="contentarea">
		<div class="pageheading">
			<h2>Home</h2>
		</div>
		<!-- Left navigation -->
		<!-- 		<div class="leftnavigation left"> -->
		<%-- 			<s:include value="leftNavigateMenu.jsp" /> --%>
		<!-- 		</div> -->
		<!-- Left navigation -->
 
		<!-- Right side table & Form -->
		<s:url action="registration" var="create" />
		<div class="rightsidecont right">
			<div class="create">
				<s:a href="#" cssClass="button button-style2 button-mini butcurve-sml">Home</s:a>
			</div>
			<div class="breadcrumb left">
				<a href="loginAction.action">Home</a>
			</div>
			<div class="clear pageheadDiv">
                    <div class="pagehead">
                        <h2>Welcome</h2>
                    </div>
                </div>
			<div class="message">
				&nbsp;&nbsp;&nbsp;&nbsp;
				<s:if test="%{deleteMessage=='true'}"></s:if>
			</div>
			<div class="clear tabledata">
				<table>
					<tr>
						<td style="text-align: center;">Welcome RSV traders</td>
					</tr>
				</table>
			</div>
		</div>
		<s:form id="delForm" name="delForm" action="deleteVendor">
			<s:hidden name="vendorId" id="vendorId" />
		</s:form>
		<!-- Right side table & Form -->

	</div>
</div>
<!-- content area -->
<s:include value="footer.jsp" />