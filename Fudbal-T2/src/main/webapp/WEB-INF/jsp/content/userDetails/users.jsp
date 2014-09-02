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
			<div style="width: 100%;">
				<label><fmt:message key="page.register.form.firstName" /></label>${user.firstName}
			</div>
			<div style="width: 100%;">
				<label><fmt:message key="page.register.form.lastName" /></label>${user.lastName}
			</div>
			<div style="width: 100%;">
				<label><fmt:message key="page.register.form.userName" /></label>${user.username}
			</div>
			<div style="width: 100%;">
				<label>Email</label>${user.email}
			</div>
			<div style="width: 100%;">
				<label><fmt:message key="page.register.form.phoneNumber" /></label>${user.phoneNumber}
			</div>
		</div>
	</c:if>
</div>