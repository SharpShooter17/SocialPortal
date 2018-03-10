<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:login>
    <div class="row">
        <c:url value="/login" var="loginUrl" />
    <%--@elvariable id="user" type="com.ujazdowski.SocialPortal.model.tables.User"--%>
    <form:form class="form-horizontal" action="${loginUrl}" name="f" method="post" modelAttribute="user">
        <fieldset>
            <legend><spring:message code="login.panel" /></legend>
            <div class="form-group">
              <form:label path="email" class="col-md-4 control-label" for="email"><spring:message code="login.email" /></form:label>
              <div class="col-md-4">
                <form:input path="email" id="email" name="email" type="text" class="form-control input-md" required="" value="${user.email}" />
              </div>
            </div>
            <div class="form-group">
              <form:label path="password" class="col-md-4 control-label" for="password"><spring:message code="login.password" /></form:label>
              <div class="col-md-4">
                <input id="password" name="password" type="password" class="form-control input-md" required="">
              </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <label class="checkbox-inline" for="remember_me">
                        <input type="checkbox" id="remember_me" name="remember-me" />
                        <spring:message code="register.rememberme" />
                    </label>
                </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label" for="signIn"></label>
              <div class="col-md-4">
                <button style="width: 100%" id="signIn" class="btn btn-success"><spring:message code="login.signin" /></button>
              </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <a href="Register"><spring:message code="login.noAccount" /></a>
                </div>
            </div>
        </fieldset>
    </form:form>
    </div>
</t:login>