<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<tiles:importAttribute name="title" />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><fmt:message key="${title}" /></title>
		<link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css" />
		<script src="<c:url value="/js/lib/jquery/jquery-1.11.1.min.js"/>"></script>
		<script src="<c:url value="/js/lib/bootstrap/bootstrap.min.js"/>"></script>
	</head>
	<body>
		<ul>
			<li><a class="dashBoard" href="<c:url value="/home"/>">Home</a></li>
			<sec:authorize ifNotGranted="ROLE_USER, ROLE_ADMIN">
				<li><a class="login" href="<c:url value="/user/login"/>"> Login</a></li>
				<li><a class="register" href="<c:url value="/register"/>">Register</a></li>
			</sec:authorize>
			<li><a href="<c:url value="/home?lang=sr" />"><fmt:message key="common.header.language.sr" /></a></li>
			<li><a href="<c:url value="/home?lang=en" />"><fmt:message key="common.header.language.en" /></a></li>
			<sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
				<li><a href="<c:url value="/j_spring_security_logout" />"><fmt:message key="page.logout.link" /></a>
			</sec:authorize>
		</ul>
			
		<section id="mainContent">
			<tiles:insertAttribute name="content" />
		</section>

	</body>
</html>
