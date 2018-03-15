<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:login>
    <c:url value="/login" var="loginUrl" />
    <%--@elvariable id="user" type="com.ujazdowski.SocialPortal.model.tables.User"--%>
    <form:form action="${loginUrl}" name="f" method="post" modelAttribute="user">
        <div class="form-group">
            <form:label path="email" for="email"><spring:message code="login.email" /></form:label>
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-envelope"></i>
                </div>
                <form:input path="email" id="email" name="email" type="email" required="required" class="form-control here"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="password" for="password"><spring:message code="login.password" /></form:label>
            <div class="input-group">
                <div class="input-group-addon">
                    <i class="fa fa-lock"></i>
                </div>
                <form:password path="password" id="password" name="password" required="required" class="form-control here" />
            </div>
        </div>

        <div class="form-group">
            <label></label>
            <div>
                <label class="custom-control custom-checkbox">
                    <input name="checkbox" id="remember-me" type="checkbox" checked="checked" class="custom-control-input" value="1">
                    <span class="custom-control-indicator"></span>
                    <span class="custom-control-description"><spring:message code="register.rememberme" /></span>
                </label>
            </div>
        </div>

        <div class="form-group">
            <form:button name="submit" type="submit" class="btn btn-primary"><spring:message code="login.signin" /></form:button>
        </div>
        <div class="form-group">
            <a href="Register"><spring:message code="login.noAccount" /></a>
        </div>
    </form:form>
</t:login>