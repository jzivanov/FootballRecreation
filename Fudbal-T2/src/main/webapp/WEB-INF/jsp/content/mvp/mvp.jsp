<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<div class="row">
	<div class="col-xs-12 col-sm-4">
		<c:choose>
			<c:when test="${not empty exhibitionId}">
				<a role="button" href="<c:url value="/mvp" />" class="btn btn-default btn-lg" style="width:100%;">
					<fmt:message key="page.mvp.history"/>
				</a>
			</c:when>
			<c:otherwise>
				<a role="button" href="<c:url value="/mvp" />" class="btn btn-info btn-lg" style="width:100%;">
					<fmt:message key="page.mvp.history"/>
				</a>
			</c:otherwise>
		</c:choose>
		<div style="width:100%;">
			<h1><small><fmt:message key="page.mvp.votting" /></small></h1>
		</div>
		<c:if test="${not empty avilex}">
			<div class="list-group" style="margin-top:20px;">
				<c:choose>
					<c:when test="${not empty exhibitionId && avilex.id == exhibitionId}">
						<c:set var="clazz" value="list-group-item active" />
					</c:when>
					<c:otherwise>
						<c:set var="clazz" value="list-group-item" />
					</c:otherwise>
				</c:choose>
  				<a href="<c:url value="/mvp/exhibition/${avilex.id}/vote" />" class="${clazz}">
   					<h4 class="list-group-item-heading"><c:out value="${avilex.location}" /></h4>
   					<p class="list-group-item-text"><c:out value="${avilex.exhibitionStart}" /></p>
  				</a>
			</div>
		</c:if>
		<div class="list-group" style="margin-top:20px;">
			<c:forEach items="${exhibitions}" var="exhibition">
			</c:forEach>
		</div>
	</div>
	<div class="col-xs-12 col-sm-8">
	<c:choose>
		<c:when test="${not empty errors}">
			<c:forEach items="${errors}" var="error">
				<div class="alert alert-info" role="alert">
					<strong>
						<fmt:message key="${error.code}" />
					</strong>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>
		<c:choose>
			<c:when test="${not empty exhibitionId}">
				<c:choose>
					<c:when test="${not empty votedPlayer}">
						<div class="alert alert-info" role="alert">
							<fmt:message key="page.mvp.votedFor" />:
							<strong>
								<a href="<c:url value="/user/users/${votedPlayer.user.id}" />">
									<c:out value="${votedPlayer.user.lastName}" /> 
									<c:out value="${votedPlayer.user.firstName}" />
								</a>
							</strong>
						</div>
					</c:when>
					<c:otherwise>
				<table class="table table-hover">
					<thead style="background-color:#31BC86; color:white; font-size:16px;">
						<tr>
							<th style="padding-top:15px; padding-bottom:15px"><fmt:message key="page.players.title" /></th>
							<th style="padding-top:15px; padding-bottom:15px"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${players}" var="player">
							<tr>
								<td>
									<c:url var="link" value="/user/users/image/${player.user.id}" />
									<img src="${link}" height="60" width="60" class="img-rounded"/>
									<a style="margin-left:10px; font-size: 28px;" href="<c:url value="/user/users/${player.user.id}" />">
										<c:out value="${player.user.lastName}" /> 
										<c:out value="${player.user.firstName}"/>
									</a>
								</td>
								<td style="vertical-align: middle;">
									<a class="btn btn-success" style="width:100%;" role="button" href="<c:url value="/mvp/exhibition/${exhibitionId}/vote/player/${player.id}" />">
										<fmt:message key="page.mvp.vote"/>
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
		<table class="table table-hover">
			<thead style="background-color:#31BC86; color:white; font-size:16px;">
				<tr>
					<th style="padding-top:15px; padding-bottom:15px"><fmt:message key="page.players.exhibitionStartTitle" /></th>
					<th style="padding-top:15px; padding-bottom:15px"><fmt:message key="page.mvp.mvpPlayer" /></th>
				</tr>
			</thead>
			<c:forEach items="${mvps}" var="player">
				<tr>
					<td style="vertical-align: middle;">
						<h2>
							<c:out value="${player.exhibition.location}" /><br />
							<small>
								<c:out value="${player.exhibition.exhibitionStart}"/>
							</small>
						</h2>
					</td>
					<td style="vertical-align: middle;">
						<c:url var="link" value="/user/users/image/${player.user.id}" />
						<img src="${link}" height="50" width="50" class="img-rounded"/>
						<h2 style="display:inline;"><a href="<c:url value="/user/users/${player.user.id}" />" style="vertical-align:middle;">
							<c:out value="${player.user.lastName}" />
							<c:out value="${player.user.firstName}"/>
						</a></h2>
					</td>
				</tr>
			</c:forEach>
		</table>
			</c:otherwise>
		</c:choose>
		</c:otherwise>
	</c:choose>
	</div>
</div>