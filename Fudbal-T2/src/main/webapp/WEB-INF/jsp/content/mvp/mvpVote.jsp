<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:forEach items="${players}" var="player">
	<td><c:out value="${player.exhibition.exhibitionStart}"/></td>
	<td><c:out value="${player.user.lastName}" /> <c:out value="${player.user.firstName}"/></td>
</c:forEach>