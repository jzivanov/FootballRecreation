<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:choose>
	<c:when test="${not empty success}">
		<div class="row">
			<div class="col-md-2">
			</div>
			<div class="col-md-8">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title"><fmt:message key="page.register.success" /></h3>
					</div>
					<div class="panel-body">
						<fmt:message key="page.register.success.message" />
					</div>
					<div class="panel-footer">
						<a class="btn btn-primary" role="button" href="<c:url value="/" />">OK</a>
					</div>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	
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
		
		<c:url value='/user/register' var="action"/>
		<form:form modelAttribute="userFormRegister" cssClass="form-horizontal" role="form" name='registerForm' action="${action}" method='POST'>
			<c:choose>
				<c:when test="${not empty erremail}"><div class="form-group has-error"></c:when>
				<c:otherwise><div class="form-group"></c:otherwise>
			</c:choose>
				<form:label path="email" cssClass="col-sm-2 control-label">
					Email
				</form:label>
    			<div class="col-sm-10">
    				<form:input path="email" type="email" cssClass="form-control" placeholder="Email" cssErrorClass="kk"/>
    				<c:if test="${not empty erremail}">
    					<fmt:message key="${erremail}" />
    				</c:if>
    			</div>
			</div>
			<c:choose>
				<c:when test="${not empty errfirstName}"><div class="form-group has-error"></c:when>
				<c:otherwise><div class="form-group"></c:otherwise>
			</c:choose>
				<form:label path="firstName" cssClass="col-sm-2 control-label">
					<fmt:message key="page.register.form.firstName" />
				</form:label>
    			<div class="col-sm-10">
    				<form:input path="firstName" cssClass="form-control"/>
    				<c:if test="${not empty errfirstName}">
    					<fmt:message key="${errfirstName}"/>
    				</c:if>
    			</div>
			</div>
			<c:choose>
				<c:when test="${not empty errlastName}"><div class="form-group has-error"></c:when>
				<c:otherwise><div class="form-group"></c:otherwise>
			</c:choose>
				<form:label path="lastName" cssClass="col-sm-2 control-label">
					<fmt:message key="page.register.form.lastName" />
				</form:label>
    			<div class="col-sm-10">
    				<form:input path="lastName" cssClass="form-control"/>
    				<c:if test="${not empty errlastName}">
    					<fmt:message key="${errlastName}"/>
    				</c:if>
    			</div>
			</div>
			<div class="form-group">
				<form:label path="phoneNumber" cssClass="col-sm-2 control-label">
					<fmt:message key="page.register.form.phoneNumber" />
				</form:label>
    			<div class="col-sm-10">
    				<form:input path="phoneNumber" cssClass="form-control"/>
    			</div>
			</div>
			<c:choose>
				<c:when test="${not empty errpassword}"><div class="form-group has-error"></c:when>
				<c:otherwise><div class="form-group"></c:otherwise>
			</c:choose>
				<form:label path="password" cssClass="col-sm-2 control-label">
					<fmt:message key="page.register.form.password" />
				</form:label>
    			<div class="col-sm-10">
    				<form:password path="password" cssClass="form-control" />
    				<c:if test="${not empty errpassword}">
    					<fmt:message key="${errpassword}"/>
    				</c:if>
    			</div>
			</div>
			<c:choose>
				<c:when test="${not empty errpassword}"><div class="form-group has-error"></c:when>
				<c:otherwise><div class="form-group"></c:otherwise>
			</c:choose>
				<form:label path="repeatPassword" cssClass="col-sm-2 control-label">
					<fmt:message key="page.register.form.password" />
				</form:label>
    			<div class="col-sm-10">
    				<form:password path="repeatPassword" cssClass="form-control" />
    			</div>
			</div>
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" name="register" class="btn btn-primary"><fmt:message key="page.register.btn.register" /></button>
    			</div>
  			</div>
		</form:form>
		</div>
	</div>
	</div>
</div>
	</c:otherwise>
</c:choose>