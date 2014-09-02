<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp"%>

<c:url var="action" value="/exhibitions" />
<sec:authorize ifAllGranted="ROLE_ADMIN">
<form:form action="${action}" method="post" modelAttribute="exhibitionForm">
	<form:label path="location"><fmt:message key="page.exhibition.new.location" /></form:label>
	<form:input path="location"/>
	<form:errors path="location"></form:errors>
	<form:label path="startDate"><fmt:message key="page.exhibition.new.startDate" /></form:label>
	<form:input path="startDate"/>
	<form:errors path="startDate"></form:errors>
	<div>
		<button type="submit" name="save"><fmt:message key="common.action.save" /></button>
	</div>
</form:form>
</sec:authorize>

<c:choose>
	<c:when test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<fmt:message key="${error.code}" />
		</c:forEach>
	</c:when>
	<c:otherwise>
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading" style="font-size: 35px">
				<h1 style="display: inline;">
					<fmt:message key="page.exhibition.title" />
				</h1>
				

			</div>


			<!-- Table -->
			<table class="table">
				<tr>
					<th><fmt:message key="page.exhibition.location" /></th>
					<th><fmt:message key="page.exhibition.starttime" /></th>
					<th><fmt:message key="page.exhibition.endtime" /></th>
					<th><fmt:message key="page.exhibition.apply" /></th>
					<th><fmt:message key="page.exhibition.players" /></th>
					<th><fmt:message key="page.exhibition.scorers" /></th>
					<th><fmt:message key="page.exhibition.mvp" /></th>
					<th><fmt:message key="page.exhibition.comments" /></th>
				</tr>
				<c:forEach items="${exhibitions}" var="exhibition">
					<c:choose>
						<c:when test="${exhibition.ended == false}">
							<tr>
								<td><c:out value="${exhibition.location}"></c:out></td>
								<td><c:out value="${exhibition.startDate}"></c:out></td>
								<td><c:out value="${exhibition.endDate}"></c:out></td>
								</br>
								<td><a
									href="<c:url value="/exhibitions/${exhibition.id}" />"><fmt:message
											key="page.exhibition.applyExhibition" /></a></td>
								<td><a
									href="<c:url value="/players/exhibition/${exhibition.id}" />"><fmt:message
											key="page.exhibition.playersExhibition" /></a></td>
								<td><a
									href="<c:url value="/comments/exhibition/${exhibition.id}" />"><fmt:message
											key="page.exhibition.commentsExhibition" /></a></td>
								<td><a
									href="<c:url value="/scorers/exhibition/${exhibition.id}" />"><fmt:message
											key="page.exhibition.scorersExhibition" /></a></td>
								<td><a
									href="<c:url value="/mvp/exhibition/${exhibition.id}" />"><fmt:message
											key="page.exhibition.mvpExhibition" /></a></td>
							</tr>
						</c:when>
						<c:otherwise>
							<td><c:out value="${exhibition.startDate}"></c:out></td>
							<td><c:out value="${exhibition.endDate}"></c:out></td>
							</br>
							<td><a
								href="<c:url value="/players/exhibition/${exhibition.id}" />"><fmt:message
										key="page.exhibition.playersExhibition" /></a></td>
							<td><a
								href="<c:url value="/comments/exhibition/${exhibition.id}" />"><fmt:message
										key="page.exhibition.commentsExhibition" /></a></td>
							<td><a
								href="<c:url value="/scorers/exhibition/${exhibition.id}" />"><fmt:message
										key="page.exhibition.scorersExhibition" /></a></td>
							<td><a
								href="<c:url value="/mvp/exhibition/${exhibition.id}" />"><fmt:message
										key="page.exhibition.mvpExhibition" /></a></td>
						</c:otherwise>
					</c:choose>

				</c:forEach>
			</table>
		</div>
	</c:otherwise>
</c:choose>

