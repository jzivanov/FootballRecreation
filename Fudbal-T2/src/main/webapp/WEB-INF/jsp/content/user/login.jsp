<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="POST" commandName="userFormLogin">
	<table>

		<tr>
			<td colspan="2" align="center"><h2>Football Login</h2></td>
		</tr>

		<tr>
			<td>E-mail :</td>
			<td><form:input path="email" /></td>
		</tr>
		<tr>
			<td>Password :</td>
			<td><form:password path="password" /></td>
		</tr>


		<tr>
			<td colspan="2"><input type="submit" value="Login"></td>
		</tr>
	</table>
</form:form>