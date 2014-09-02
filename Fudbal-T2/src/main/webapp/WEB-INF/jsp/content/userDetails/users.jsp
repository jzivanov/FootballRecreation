<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<div class="row">
	<div class="col-md-4">
		<div class="list-group">
			<c:set value="list-group-item" var="clazz" />
			<c:forEach items="${users}" var="us">
				<c:choose>
					<c:when test="${not empty user}">
						<c:choose>
							<c:when test="${user.id == us.id}">
								<c:set value="list-group-item active" var="clazz" />
							</c:when>
							<c:otherwise>
								<c:set value="list-group-item" var="clazz" />
							</c:otherwise>
						</c:choose>
					</c:when>
					<c:otherwise>
						<c:set value="list-group-item" var="clazz" />
					</c:otherwise>
				</c:choose>
				<a href="<c:url value="/user/users/${us.id}" />" class="${clazz}">
					<c:out value="${us.firstName}"></c:out>
					<c:out value="${us.lastName}"></c:out>
			 	</a>
			</c:forEach>
		</div>
	</div>
	<c:if test="${not empty user}">
		<div class="col-md-8">
			<div style="width: 90%; background-color: #B088BE; color:#fff; line-height: 60px; margin-bottom: 20px;">
				<h4 style="border:1px solid #B088BE; display: inline; margin-left:20px;">
					${user.firstName}
					${user.lastName}</h4>
			</div>
				<div class="row" style="width: 90%; padding: 15px; border:1px solid #D2AADF; border-left-width: 2px; margin-bottom:10px; margin-left:0px;">
					<div class="col-md-4">
						<span style="color:#777;"><fmt:message key="page.register.form.firstName" /></span>
					</div>
					<div class="col-md-8">
						${user.firstName}
					</div>
				</div>
				<div class="row" style="width: 90%; padding: 15px; border:1px solid #D2AADF; border-left-width: 2px; margin-bottom:10px;  margin-left:0px;">
					<div class="col-md-4">
						<span style="color:#777;"><fmt:message key="page.register.form.lastName" /></span>
					</div>
					<div class="col-md-8">
						${user.lastName}
					</div>
				</div>
				<div class="row" style="width: 90%; padding: 15px; border:1px solid #D2AADF; border-left-width: 2px; margin-bottom:10px;  margin-left:0px;">
					<div class="col-md-4">
						<span style="color:#777;"><fmt:message key="page.register.form.userName" /></span>
					</div>
					<div class="col-md-6">
						${user.username}
					</div>
				</div>
				<div class="row" style="width: 90%; padding: 15px; border:1px solid #D2AADF; border-left-width: 2px; margin-bottom:10px;  margin-left:0px;">
					<div class="col-md-4">
						<span style="color:#777;"><fmt:message key="page.register.form.email" /></span>
					</div>
					<div class="col-md-8">
						${user.email}
					</div>
				</div>
				<div class="row" style="width: 90%; padding: 15px; padding-right:0; border:1px solid #D2AADF; border-left-width: 2px; margin-bottom:10px;  margin-left:0px;">
					<div class="col-md-4">
						<span style="color:#777;"><fmt:message key="page.register.form.phoneNumber" /></span>
					</div>
					<div class="col-md-8">
						${user.phoneNumber}
					</div>
				</div>
				<div class="row" style="width: 90%; padding: 15px; padding-right:0; border:1px solid #31BC86; border-left-width: 2px; margin-bottom:10px;  margin-left:0px;">
					<div class="col-md-12">
						<button type="button" class="btn btn-success">Primary</button>
					</div>
				</div>
		</div>
	</c:if>
</div>