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
<c:url var="get_home" value="/home"></c:url>
<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="${get_home}"><fmt:message key="common.header.title" /></a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
			 		<li>
			 			<a href="<c:url value="/exhibitions" />"><fmt:message key="page.exhibition.title" /></a>
			 		</li>
			 		<li><a href="<c:url value="/user/users" />"><fmt:message key="page.users.title" /></a>
				</sec:authorize>
				<sec:authorize ifNotGranted="ROLE_USER, ROLE_ADMIN">
					<li><a class="login" href="<c:url value="/user/login"/>"> Login</a></li>
					<li><a class="register" href="<c:url value="/user/register"/>">Register</a></li>
				</sec:authorize>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/home?lang=sr" />"><fmt:message key="common.header.language.sr" /></a></li>
				<li><a href="<c:url value="/home?lang=en" />"><fmt:message key="common.header.language.en" /></a></li>
				<sec:authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
					<li class="dropdown">
          				<a href="#" class="dropdown-toggle" data-toggle="dropdown"><fmt:message key="common.header.user" /><span class="caret"></span></a>
          				<ul class="dropdown-menu" role="menu">
           					<li><a href="#"><fmt:message key="common.header.userProfile" /></a></li>
            				<li class="divider"></li>
            				<li><a href="<c:url value="/j_spring_security_logout" />"><fmt:message key="page.logout.link" /></a></li>
          				</ul>
        			</li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
	
<section id="mainContent">
	<div class="row">
		<div class="col-sm-1 col-md-1"></div>
		<div class="col-sm-10 col-md-10">
			<tiles:insertAttribute name="content" />
		</div>
	</div>
</section>

</body>
</html>
