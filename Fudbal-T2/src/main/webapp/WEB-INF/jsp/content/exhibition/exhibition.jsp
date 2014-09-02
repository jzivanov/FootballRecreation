<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>




<div class="panel panel-default">
	<!-- Default panel contents -->
	<div class="panel-heading" style="font-size: 35px">
		<fmt:message key="page.exhibition.title" />
	</div>


	<!-- Table -->
	<table class="table">
		<tr>
			<th><fmt:message key="page.exhibition.starttime" /></th>
			<th><fmt:message key="page.exhibition.endtime" /></th>
			<th><fmt:message key="page.exhibition.apply" /></th>
			<th><fmt:message key="page.exhibition.comments" /></th>
			<th><fmt:message key="page.exhibition.scorers" /></th>
			<th><fmt:message key="page.exhibition.mvp" /></th>
		</tr>
		<c:forEach items="${exhibitions}" var="exhibition">
			<tr>
				<td><c:out value="${exhibition.startDate}"></c:out></td>
				<td><c:out value="${exhibition.endDate}"></c:out></td>
				</br>
				<td><a href="<c:url value="/exhibitions/${exhibition.id}" />"><fmt:message key="page.exhibition.applyExhibition" /></a></td>
				<td><a href="<c:url value="/comments/exhibition/${exhibition.id}" />"><fmt:message key="page.exhibition.commentsExhibition" /></a></td>
				<td><a href="<c:url value="/scorers/exhibition/${exhibition.id}" />"><fmt:message key="page.exhibition.scorersExhibition" /></a></td>
				<td><a href="<c:url value="/mvp/exhibition/${exhibition.id}" />"><fmt:message key="page.exhibition.mvpExhibition" /></a></td>
			</tr>
		</c:forEach>
	</table>
</div>

