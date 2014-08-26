<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:forEach items="${commentsMap}" var="commentsMap">
	<c:out value="${commentsMap.key.content}" />
	
	<c:forEach items="${commentsMap.value}" var="comments">
		<c:out value="${comments.content}" />
	</c:forEach>
	
</c:forEach>