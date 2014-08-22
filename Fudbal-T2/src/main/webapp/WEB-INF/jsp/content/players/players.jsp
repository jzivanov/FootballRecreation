<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:set var="home" value="<%= rs.tfzr.FudbalT2.model.Player.Team.Home %>"></c:set>
<c:set var="away" value="<%= rs.tfzr.FudbalT2.model.Player.Team.Away %>"></c:set>
<c:set var="none" value="<%= rs.tfzr.FudbalT2.model.Player.Team.None %>"></c:set>
<h1>Team home</h1>
<c:forEach items="${players}" var="player">
	<tr>
		<c:if test="${player.team == home}">
			<td><c:out value="${player.exhibition.exhibitionStart}"></c:out></td>
			<td><c:out value="${player.user.firstName}"></c:out></td>
			<td><c:out value="${player.user.lastName}"></c:out></td>
			<td><c:out value="${player.team}"></c:out></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/0" />">None</a></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/2" />">Away</a></td>
		</c:if>
	</tr>
</c:forEach>
<h1>Team away</h1>
<c:forEach items="${players}" var="player">
	<tr>
		<c:if test="${player.team == away}">
			<td><c:out value="${player.exhibition.exhibitionStart}"></c:out></td>
			<td><c:out value="${player.user.firstName}"></c:out></td>
			<td><c:out value="${player.user.lastName}"></c:out></td>
			<td><c:out value="${player.team}"></c:out></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/0" />">None</a></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/1" />">Home</a></td>
		</c:if>
	</tr>
</c:forEach>
<h1>Not assigned players</h1>
<c:forEach items="${players}" var="player">
	<tr>
		<c:if test="${player.team == none}">
			<td><c:out value="${player.exhibition.exhibitionStart}"></c:out></td>
			<td><c:out value="${player.user.firstName}"></c:out></td>
			<td><c:out value="${player.user.lastName}"></c:out></td>
			<td><c:out value="${player.team}"></c:out></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/1" />">Home</a></td>
			<td><a href="<c:url value="/players/exhibition/${player.exhibition.id}/player/${player.id}/team/2" />">Away</a></td>
		</c:if>
	</tr>
</c:forEach>