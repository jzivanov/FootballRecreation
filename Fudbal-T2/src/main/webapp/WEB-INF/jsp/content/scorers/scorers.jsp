<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

	<div class="row">
		<div class="col-sm-4">
			<c:choose>
				<c:when test="${not empty exhibitionId}">
					<a role="button" href="<c:url value="/scorers" />" class="btn btn-default btn-lg" style="width:100%;">
						<fmt:message key="page.scorers.ranklist.overall"/>
					</a>
				</c:when>
				<c:otherwise>
					<a role="button" href="<c:url value="/scorers" />" class="btn btn-info btn-lg" style="width:100%;">
						<fmt:message key="page.scorers.ranklist.overall"/>
					</a>
				</c:otherwise>
			</c:choose>
			<div style="width:100%;">
				<h1><small><fmt:message key="page.exhibition.title" /></small></h1>
			</div>
			<div class="list-group" style="margin-top:20px;">
				<c:forEach items="${exhibitions}" var="exhibition">
					<c:choose>
						<c:when test="${not empty exhibitionId && exhibition.id == exhibitionId}">
							<c:set var="clazz" value="list-group-item active" />
						</c:when>
						<c:otherwise>
							<c:set var="clazz" value="list-group-item" />
						</c:otherwise>
					</c:choose>
					<a href="<c:url value="/scorers/exhibition/${exhibition.id}"/>" class="${clazz}">
						<h4 class="list-group-item-heading"><c:out value="${exhibition.location}" /></h4>
						<p class="list-group-item-text"><c:out value="${exhibition.exhibitionStart}" /></p>
					</a>
				</c:forEach>
			</div>
		</div>
		<div class="col-sm-8">
			<sec:authorize ifAllGranted="ROLE_ADMIN">
				<c:if test="${not empty scorerform}">
					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title"><strong><fmt:message key="page.scorers.addPlayers" /></strong></h3>
						</div>
						<div class="panel-body">
					<c:url value="/scorers/exhibition/${exhibitionId}/add" var="action"/>
					<form:form action="${action}" cssClass="form-inline row" role="form" method="post" modelAttribute="scorerform">
						<div class="form-group col-sm-5">
							<form:select path="playerId" cssClass="form-control" style="width:100%">
								<form:options items="${players}" itemLabel="name" itemValue="id"/>
							</form:select>
						</div>
						<div class="form-group col-sm-5">
							<form:input path="goalCount" cssClass="form-control" style="width:100%"/>
						</div>
						<div class="form-group col-sm-2">
							<button type="submit" class="btn btn-primary" style="width:100%">
								<fmt:message key="common.action.add" />
							</button>
						</div>
					</form:form>
						</div>
					</div>
				</c:if>
			</sec:authorize>
			<table class="table table-hover">
				<thead style="background-color:#31BC86; color:white; font-size:16px;">
					<tr>
						<th style="padding-top:15px; padding-bottom:15px"><fmt:message key="page.scorers.player" /></th>
						<th style="padding-top:15px; padding-bottom:15px"><fmt:message key="page.scorers.goals" /></th>
					</tr>
				</thead>
				<c:forEach items="${scorers}" var="scorer">
					<tr>
						<td>
							<c:url var="link" value="/user/users/image/${scorer.key.player.user.id}" />
							<img src="${link}" height="60" width="60" class="img-rounded"/>
							<a style="margin-left:10px; font-size: 28px;" href="<c:url value="/user/users/${scorer.key.player.user.id}" />">
								<c:out value="${scorer.key.player.user.lastName}" /> 
								<c:out value="${scorer.key.player.user.firstName}"/>
								<c:out value="${scorer.key.player.team}"></c:out>
							</a>
						</td>
						<td><c:out value="${scorer.value}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>