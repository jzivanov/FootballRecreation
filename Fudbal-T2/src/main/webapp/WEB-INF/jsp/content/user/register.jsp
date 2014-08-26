<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form method="POST" commandName="userFormRegister">
	<table>

		<tr>
			<td colspan="2" align="center"><h2>Football Registration</h2></td>
		</tr>

		<tr>
			<td>First Name :</td>
			<td><form:input path="firstName" /></td>
		</tr>
		<tr>
			<td>Last Name :</td>
			<td><form:input path="lastName" /></td>
		</tr>
		<tr>
			<td>Phone Number :</td>
			<td><form:input path="phoneNumber" /></td>
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
			<td>Repeat Password :</td>
			<td><form:password path="confirmPassword" /></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="Register"></td>
		</tr>
	</table>
</form:form>

