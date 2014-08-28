<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<script>
<s:if test="%{#session.get('USER') != null}">
 window.location="loginAction.action";
</s:if>
</script>
<s:include value="header.jsp" />
<div class="innercontainer">
	<div class="contentarea">
		<div class="clear">&nbsp;</div>
		<!--Login Box -->
		<div class="padd-20">&nbsp;</div>
		<div class="loginbox">
			<div style="width: 100%;">
				<h2>Login</h2>
			</div>
			<s:form id="loginForm" name="loginForm" action="loginAction">
				<s:if test="hasActionErrors()">
					<div class="errorDiv clear">
						<s:actionerror />
					</div>
				</s:if>

				<p>User Name:</p>
				<div>
					<div class="txtboximg">
						<!-- 						<img src="images/userico.png" /> -->
						<span class="fa-user" style="font-size: 30px; color: gray; padding-left: 5px;"></span>
					</div>

					<s:textfield theme="simple" size="40" name="user.userName" value="" />
				</div>
				<p>Password:</p>
				<div>
					<div class="txtboximg">
						<!-- 											<img src="images/pwdico.png" /> -->
						<span class="fa-lock" style="font-size: 30px; color: gray; padding-left: 5px;"></span>
					</div>
					<s:password theme="simple" size="30" name="user.passWord" value="" />
				</div>
				<div class="clear">&nbsp;</div>
				<div align="right">
					<s:submit cssClass="button-style2 button-small1 butcurve-sml btn-stacked-big" cssStyle="width:100%:height:50px;" theme="simple" value="Login" label="Login" />
				</div>
				<!-- 				<div class="clear">&nbsp;</div> -->
				<!-- 				<div align="right"> -->
				<%-- 					<s:reset cssClass="button-style1 button-small1 butcurve-sml btn-stacked-big" theme="simple" value="Cancel" label="Cancel" /> --%>
				<!-- 				</div> -->
				<p>
					&nbsp;
					<!-- <a href="#">Forget Password</a>-->
				</p>
				<div class="clear">&nbsp;</div>
			</s:form>
		</div>
		<!-- 		<div class="bottom"> -->
		<%--<s:url var="reg" action="registration"></s:url>
				<s:a href="%{reg}"><img src="images/signupico.png" align="absmiddle" />New user signup</s:a> --%>
		<!-- 		</div> -->
		<!--Login Box -->

	</div>
</div>
<!-- content area -->

<s:include value="footer.jsp" />



<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> --%>
<%-- <%@taglib prefix="s" uri="/struts-tags"%> --%>
<%-- <%@ taglib uri="/struts-jquery-tags" prefix="sj"%> --%>
<!-- <!DOCTYPE html> -->

<%-- <s:include value="header.jsp" /> --%>
<!-- <!-- content area -->
<!-- <!-- content area -->
<%-- <s:include value="footer.jsp" /> --%>