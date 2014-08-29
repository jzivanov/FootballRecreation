<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:if test="${not empty error}">
	<p><fmt:message key="${error}" /></p>
</c:if>

<c:if test="${not empty logout_msg}">
	<p><fmt:message key="${logout_msg}" /></p>
</c:if>

<form name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
	<table>
		<tr>
			<td>User:</td>
			<td><input type="text" name="username" value=''></td>
		</tr>
		<tr>
			<td>Password:</td>
			<td><input type="password" name="password" /></td>
		</tr>
		<tr>
			<td><input name="submit" type="submit" value="submit" /></td>
		</tr>
	</table>
</form>