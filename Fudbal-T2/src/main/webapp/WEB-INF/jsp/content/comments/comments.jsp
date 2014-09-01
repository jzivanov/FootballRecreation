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
		<form:form id="formComment" action="${post_url}" method="post" modelAttribute="comment" role="form">
			<div class="row">
				<div class="col-xs-12 col-sm-8 col-md-8">
					<c:choose>
						<c:when test="${not empty mainComment}">
							<c:set var="formTitleClass" value="panel panel-info"></c:set>
						</c:when>
						<c:otherwise>
							<c:set var="formTitleClass" value="panel panel-primary"></c:set>
						</c:otherwise>
					</c:choose>
					<div class="${formTitleClass}">
  						<div class="panel-heading">
  							<c:choose>
  								<c:when test="${not empty mainComment}">
    								<h3 class="panel-title"><strong><fmt:message key="page.comments.replyToCommentTitle" />: ${mainComment.title}</strong></h3>
  								</c:when>
  								<c:otherwise>
    								<h3 class="panel-title"><strong><fmt:message key="page.comments.insertCommentTitle" /></strong></h3>
  								</c:otherwise>
  							</c:choose>
  						</div>
  						<div class="panel-body">
							<form:hidden path="exhibitionId"/>
							<c:if test="${not empty comment.mainCommentId}">
								<form:hidden path="mainCommentId" />
							</c:if>
							<div class="form-group">
								<form:label path="title"><fmt:message key="page.comments.comment.title" /></form:label>
								<form:input path="title" cssClass="form-control" cssErrorClass="" />
							</div>
							<div class="form-group">
								<form:label path="body"><fmt:message key="page.comments.comment.body" /></form:label>
								<form:textarea path="body" cssClass="form-control" rows="3" cssErrorClass=""/>
							</div>
							<div>
								<button type="submit" name="save" class="btn btn-primary btn-lg">
									<fmt:message key="common.action.save" />
								</button>
								<button type="submit" name="cancel" class="btn btn-warning btn-lg">
									<fmt:message key="common.action.cancel" />
								</button>
							</div>
						</div>
					</div>
  				</div>
			</div>
		</form:form>
		
		<div class="row" style="margin-top:20px;">
		<div class="col-xs-12 col-sm-8 col-md-8">
		<legend><fmt:message key="page.comments.title" />:</legend>
			<c:forEach items="${commentsMap}" var="commentsMap">
				<c:choose>
					<c:when test="${userId == commentsMap.key.user.id}">
						<c:set var="mainCommClass" value="panel panel-primary" />
					</c:when>
					<c:otherwise>
						<c:set var="mainCommClass" value="panel panel-default" />
					</c:otherwise>
				</c:choose>
				<div class="${mainCommClass}">
  					<div class="panel-heading">
    					<h3 class="panel-title">
    						<a href="<c:url value="/users/${commentsMap.key.user.id}" />">
    							[<c:out value="${commentsMap.key.user.username}" />]
    						</a>
    						<c:out value="${commentsMap.key.title}" />
    					</h3>
  					</div>
  					<div class="panel-body">
    					<c:out value="${commentsMap.key.body}"></c:out>
  					</div>
  					<div class="panel-footer">
  						<c:if test="${userId == commentsMap.key.user.id}">
							<a href="<c:url value="/comments/remove/${commentsMap.key.id}" />">
								<fmt:message key="page.comments.remove" />
							</a>
						</c:if>
						<a href="<c:url value="/comments/exhibition/${commentsMap.key.exhibition.id}/add/${commentsMap.key.id}" />">
							<fmt:message key="page.comments.reply" />
						</a>
  					</div>
				</div>
				
				<c:forEach items="${commentsMap.value}" var="comments">
					<c:choose>
						<c:when test="${userId == comments.user.id}">
							<c:set var="subCommClass" value="panel panel-info" />
						</c:when>
						<c:otherwise>
							<c:set var="subCommClass" value="panel panel-default" />
						</c:otherwise>
					</c:choose>
					<div class="${subCommClass}" style="width:90%; margin-left:10%;">
  						<div class="panel-heading">
    						<h3 class="panel-title">
    							<a href="<c:url value="/users/${comments.user.id}" />">
    								[<c:out value="${comments.user.username}" />]
    							</a>
    							<c:out value="${comments.title}" />
    						</h3>
  						</div>
  						<div class="panel-body">
    						<c:out value="${comments.body}" />
  						</div>
  						<div class="panel-footer">
							<c:if test="${userId == comments.user.id}">
								<a href="<c:url value="/comments/remove/${comments.id}" />">
									<fmt:message key="page.comments.remove" />
								</a>
							</c:if>
  						</div>
					</div>
				</c:forEach>
			</c:forEach>
		</div>
		</div>
	</c:otherwise>
</c:choose>