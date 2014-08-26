<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<h1>Exhibitions</h1>
<table>
	<tr>
		<th>Time exhibition starts:</th>
		<th>Time exhibition ends:</th>
		<th>Apply for exhibition:</th>
	</tr>
	<c:forEach items="${exhibitions}" var="exhibition">
	<tr>
		<td><c:out value="${exhibition.startDate}"></c:out></td>
		<td><c:out value="${exhibition.endDate}"></c:out></td></br>
		<td><a href="<c:url value="/exhibitions/${exhibition.id}/user/${exhibition.userId}" />">Apply</a></td>
	</tr>
</c:forEach>

</table>

