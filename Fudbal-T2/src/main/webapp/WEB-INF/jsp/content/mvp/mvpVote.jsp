<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<p>Voting for exhibition. <a href="<c:url value="/exhibition/${mvp.exhibition.id}" ></c:url>"></a></p>
<p>Exhibition started at: <c:out value="${mvp.exhibition.exhibitionStart}" /></p>

<c:choose>
	<c:when test="${not empty errors}">
		<c:forEach items="${errors}" var="error">
			<fmt:message key="${error.code}" />
		</c:forEach>
	</c:when>
	<c:otherwise>
		<table>
			<tr>
				<th>User</th>
				<th>Vote</th>
			</tr>
			<c:forEach items="${mvp.players}" var="player">
				<tr>
					<td>
						<c:out value="${player.user.lastName}" /> <c:out value="${player.user.firstName}"/>
					</td>
					<td>
						<a href="<c:url value="/mvp/exhibition/${mvp.exhibition.id}/vote/player/${player.id}" />">Vote</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:otherwise>
</c:choose>