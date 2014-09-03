<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ include file="/WEB-INF/jsp/common/tagLibs.jsp" %>

<c:choose>
	<c:when test="${userform.id == signeduserid}">
		<c:set var="canshow" value="true" />
	</c:when>
	<c:otherwise>
		<c:set var="canshow" value="false" />
	</c:otherwise>
</c:choose>
<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="panel panel-info">
			<div class="panel-heading">
    			<h3 class="panel-title"><fmt:message key="page.login.fillForm" /></h3>
  			</div>
			<div class="panel-body">
				<c:url var="action" value="/user/users/edit" />
				<form:form modelAttribute="userform" cssClass="form-horizontal" role="form" name='userform' action="${action}" method='POST'>
					<form:hidden path="id"/>
					<c:if test="${canshow}">
					<c:choose>
						<c:when test="${not empty erremail}">
							<c:set var="emailc" value="form-group has-error" />
						</c:when>
						<c:otherwise>
							<c:set var="emailc" value="form-group" />
						</c:otherwise>
					</c:choose>
					<div class="${emailc}">
						<form:label path="username" cssClass="col-sm-2 control-label">
							Email
						</form:label>
   						<div class="col-sm-10">
   							<form:input path="username" type="email" cssClass="form-control"/>
   							<c:if test="${not empty erremail}">
   								<fmt:message key="${erremail}" />
   							</c:if>
   						</div>
					</div>
					</c:if>
					<c:choose>
						<c:when test="${not empty errfirstName}">
							<c:set var="fnc" value="form-group has-error" />
						</c:when>
						<c:otherwise>
							<c:set var="fnc" value="form-group" />
						</c:otherwise>
					</c:choose>
					<div class="${fnc}">
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
						<c:when test="${not empty errlastName}">
							<c:set var="lnc" value="form-group has-error" />
						</c:when>
						<c:otherwise>
							<c:set var="lnc" value="form-group" />
						</c:otherwise>
					</c:choose>
					<div class="${lnc}">
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
						<form:label path="phone" cssClass="col-sm-2 control-label">
							<fmt:message key="page.register.form.phoneNumber" />
						</form:label>
    					<div class="col-sm-10">
    						<form:input path="phone" cssClass="form-control"/>
    					</div>
					</div>
					<sec:authorize ifAllGranted="ROLE_ADMIN">
						<div class="form-group">
							<label class="col-sm-2 control-label">Admin:</label>
							<div class="cold-sm-10">
								<div class="btn-group" data-toggle="buttons" style="margin-left:20px; margin-right:20px;">
  								<c:choose>
  									<c:when test="${userform.admin}">
  										<c:set var="admin" value="checked='checked'" />
  										<c:set var="notadmin" value="checked='false'"/>
  										<c:set var="adminc" value="active" />
  										<c:set var="notadminc" value=""/>
  									</c:when>
  									<c:otherwise>
  										<c:set var="admin" value="checked='false'" />
  										<c:set var="notadmin" value="checked='checked'"/>
  										<c:set var="adminc" value="" />
  										<c:set var="notadminc" value="active"/>
  									</c:otherwise>
  								</c:choose>
  								<label class="btn btn-primary ${adminc}">
    								<input type="radio" name="admin" id="adminno" ${admin}> DA
  								</label>
 								<label class="btn btn-primary ${notadminc}">
    								<input type="radio" name="admin" id="adminno" ${notadmin}> NE
  								</label>
								</div>
							</div>
						</div>
					</sec:authorize>
					<c:choose>
						<c:when test="${not empty errpassword}">
							<c:set var="passc" value="form-group has-error" />
						</c:when>
						<c:otherwise>
							<c:set var="passc" value="form-group" />
						</c:otherwise>
					</c:choose>
					<c:if test="${canshow}">
					<div class="${passc}">
						<form:label path="password" cssClass="col-sm-2 control-label">
							<fmt:message key="page.register.form.password" />
						</form:label>
    					<div class="col-sm-10">
    						<form:password path="password" cssClass="form-control" showPassword="true"/>
    						<c:if test="${not empty errpassword}">
    							<fmt:message key="${errpassword}"/>
    						</c:if>
    					</div>
					</div>
					<div class="${passc}">
						<form:label path="repeatPassword" cssClass="col-sm-2 control-label">
							<fmt:message key="page.register.form.password" />
						</form:label>
    					<div class="col-sm-10">
    						<form:password path="repeatPassword" cssClass="form-control" showPassword="true"/>
    					</div>
					</div>
					</c:if>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" name="edit" class="btn btn-primary"><fmt:message key="common.action.save" /></button>
    					</div>
  					</div>
				</form:form>
			</div>
  		</div>
  	</div>
</div>