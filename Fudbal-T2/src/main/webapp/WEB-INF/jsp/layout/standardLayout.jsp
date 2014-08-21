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
	
		<section id="mainContent">
			<tiles:insertAttribute name="content" />
		</section>

	</body>
</html>
