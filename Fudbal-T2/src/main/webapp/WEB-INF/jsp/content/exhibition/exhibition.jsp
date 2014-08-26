<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<h1>Exhibitions</h1>
<c:forEach items="${exhibition}" var="exhibitions">
	<tr>
		<td><c:out value="${exhibition.exhibitionStart}"></c:out></td>
		<td><c:out value="${exhibition.exhibitionEnd}"></c:out></td>
	</tr>
</c:forEach>