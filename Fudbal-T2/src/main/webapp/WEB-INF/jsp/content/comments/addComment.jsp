<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:url var="action" value="/comments" />
<form:form id="formComment" action="${action}" method="post" modelAttribute="comment">
	<fieldset>
		<form:hidden path="exhibitionId"/>
		<c:if test="${not empty comment.mainCommentId}">
			<form:hidden path="mainCommentId" />
		</c:if>
		<form:label path="title"><fmt:message key="page.comments.comment.title" /></form:label>
		<form:input path="title"/>
		<form:errors path="title" />
		<form:input path="body"/>
		<form:errors path="body" />
	</fieldset>
	<div>
		<button type="submit" name="save"><fmt:message key="common.action.save" /></button>
	</div>
</form:form>