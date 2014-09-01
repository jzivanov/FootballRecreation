<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
	<div class="panel panel-info">
		<div class="panel-heading">
    		<h3 class="panel-title"><fmt:message key="page.login.fillForm" /></h3>
  		</div>
	<div class="panel-body">
		<c:if test="${not empty error}">
			<div class="col-sm-2"></div>
			<div class="col-sm-10 alert alert-danger alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only">Close</span>
				</button>
				<strong><fmt:message key="common.alter.warning" /> </strong><fmt:message key="${error}" />
			</div>
		</c:if>

		<c:if test="${not empty logout_msg}">
			<div class="col-sm-2"></div>
			<div class="col-sm-10 alert alert-success" role="alert">
				<fmt:message key="${logout_msg}" />
			</div>
		</c:if>
		
		<form class="form-horizontal" role="form" name='loginForm' action="<c:url value='/j_spring_security_check' />" method='POST'>
			<div class="form-group">
				<label for="email" class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-10">
      				<input type="email" name="username" class="form-control" id="email" placeholder="Email">
    			</div>
    		</div>
  			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label"><fmt:message key="page.login.password" /></label>
    			<div class="col-sm-10">
      				<input type="password" name="password" class="form-control" id="password" placeholder="<fmt:message key="page.login.password" />">
    			</div>
  			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" name="submit" class="btn btn-primary"><fmt:message key="page.login.signin" /></button>
    			</div>
  			</div>
		</form>
	</div>
	</div>
	</div>
</div>