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
				<div class="row">
					<div class="col-xs-12 col-sm-2">
						<c:url var="link" value="/user/users/image/${userform.id}" />
						<img src="${link}" height="80" width="80" class="img-rounded"/>
					</div>
					<div class="col-xs-12 col-sm-10">
    					<h1 class="panel-title" style="display:inline; font-size:32px;">
    						<c:out value="${userform.firstName}"/> <c:out value="${userform.lastName}" />
    						<small style="display:block;"><fmt:message key="page.login.fillForm" /></small>
    					</h1>
					</div>
				</div>
  			</div>
			<div class="panel-body">
				<c:url var="action" value="/user/users/edit" />
				<form:form enctype="multipart/form-data" modelAttribute="userform" cssClass="form-horizontal" role="form" name='userform' action="${action}" method='POST' autocomplete="off">
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
   								<p class="text-danger"><fmt:message key="${erremail}" /></p>
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
    							<p class="text-danger"><fmt:message key="${errfirstName}"/></p>
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
    							<p class="text-danger"><fmt:message key="${errlastName}"/></p>
    						</c:if>
    					</div>
					</div>
					<c:choose>
						<c:when test="${not empty imgerrors}">
							<c:set var="imgc" value="form-group has-error" />
						</c:when>
						<c:otherwise>
							<c:set var="imgc" value="form-group" />
						</c:otherwise>
					</c:choose>
					<div class="${imgc}">
						<form:label path="imagedto.data" cssClass="col-sm-2 control-label">
							<fmt:message key="page.user.image" />
						</form:label>
						<div class="col-sm-10">
							<form:input path="imagedto.data" type="file"/>
							<c:if test="${not empty imgerrors}">
								<c:forEach items="${imgerrors}" var="error">
									<p class="text-danger"><fmt:message key="${error.code}" /></p>
								</c:forEach>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<form:label path="phone" cssClass="col-sm-2 control-label">
							<fmt:message key="page.register.form.phoneNumber" />
						</form:label>
    					<div class="col-sm-10">
    						<form:input path="phone" cssClass="form-control" autocomplete='off'/>
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
  								<label class="btn btn-default ${adminc}">
    								<input type="radio" name="admin" id="adminno" ${admin}> <fmt:message key="common.action.yes" />
  								</label>
 								<label class="btn btn-default ${notadminc}">
    								<input type="radio" name="admin" id="adminno" ${notadmin}> <fmt:message key="common.action.no" />
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
    						<form:password path="password" cssClass="form-control" showPassword="true" autocomplete='off'/>
    						<c:if test="${not empty errpassword}">
    							<p class="text-danger"><fmt:message key="${errpassword}"/></p>
    						</c:if>
    					</div>
					</div>
					<div class="${passc}">
						<form:label path="repeatPassword" cssClass="col-sm-2 control-label">
							<fmt:message key="page.register.form.password" />
						</form:label>
    					<div class="col-sm-10">
    						<form:password path="repeatPassword" cssClass="form-control" showPassword="true" autocomplete='off'/>
    					</div>
					</div>
					</c:if>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" name="edit" class="btn btn-primary">
      							<fmt:message key="common.action.save" />
      						</button>
      						<a role="button" href="<c:url value='/user/users/${userform.id}' />" class="btn btn-danger">
      							<fmt:message key="common.action.cancel" />
      						</a>
    					</div>
  					</div>
				</form:form>
			</div>
  		</div>
  	</div>
</div>