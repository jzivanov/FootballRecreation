<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<tiles:importAttribute name="title" />

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><fmt:message key="${title}" /></title>
	</head>
	<body>
			<ul>
				<li><a class="dashBoard" href="<c:url value="/home"/>">Home</a></li>
				<li><a class="login" href="<c:url value="/login"/>"> Login</a></li>
				<li><a class="register" href="<c:url value="/register"/>">Register</a></li>
				<li><a href="<c:url value="/home?lang=sr" />"><fmt:message key="common.header.language.sr" /></a></li>
				<li><a href="<c:url value="/home?lang=en" />"><fmt:message key="common.header.language.en" /></a></li>
			</ul>
			
		<section id="mainContent">
			<tiles:insertAttribute name="content" />
		</section>

	</body>
</html>
