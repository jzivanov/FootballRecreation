<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:choose>
	<c:when test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<fmt:message key="${error.code}" />
		</c:forEach>
	</c:when>
	<c:otherwise>
		<c:url var="post_url"  value="/comments" />
		<form:form id="formComment" action="${post_url}" method="post" modelAttribute="comment">
			<fieldset>
				<form:hidden path="exhibitionId"/>
				<form:label path="title"><fmt:message key="page.comments.comment.title" /></form:label>
				<form:input path="title"/>
				<form:input path="body"/>
			</fieldset>
			<div>
				<button type="submit" name="save"><fmt:message key="common.action.save" /></button>
			</div>
		</form:form>

		<c:forEach items="${commentsMap}" var="commentsMap">
			<b>Title</b>
			<c:out value="${commentsMap.key.title}" />
			<br />
			<c:out value="${commentsMap.key.body}"></c:out>
	
			<c:if test="${userId == commentsMap.key.user.id}">
				<a href="<c:url value="/comments/remove/${commentsMap.key.id}" />"><fmt:message key="page.comments.remove" /></a>
			</c:if>
			<a href="<c:url value="/comments/exhibition/${commentsMap.key.exhibition.id}/add/${commentsMap.key.id}" />"><fmt:message key="page.comments.reply" /></a>
	
			<c:forEach items="${commentsMap.value}" var="comments">
				<b>Title</b>
				<c:out value="${comments.title}" />
				<br />
				<c:out value="${comments.body}" />
				<c:if test="${userId == comments.user.id}">
					<a href="<c:url value="/comments/remove/${comments.id}" />"><fmt:message key="page.comments.remove" /></a>
				</c:if>
			</c:forEach>
		</c:forEach>
	</c:otherwise>
</c:choose>