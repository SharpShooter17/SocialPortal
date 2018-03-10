<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<t:login>
    <%--@elvariable id="user" type="com.ujazdowski.SocialPortal.model.tables.User"--%>
    <form:form class="form-horizontal" action="/Register" method="post" modelAttribute="user">
        <fieldset>
            <!-- Form Name -->
            <legend><spring:message code="register.panel" /></legend>
            <!-- Text input-->
            <div class="form-group">
                <form:label path="firstName" class="col-md-4 control-label" for="firstName"><spring:message code="register.firstname" /></form:label>
                <div class="col-md-4">
                    <form:input id="firstName" name="firstName" type="text" placeholder="" class="form-control input-md" required="" path="firstName" />
                    <form:errors path="firstName" cssErrorClass="text-danger" cssClass="alert alert-danger" />
                </div>
            </div>
            <!-- Text input-->
            <div class="form-group">
                <form:label path="secondName" class="col-md-4 control-label" for="secondName"><spring:message code="register.secondname" /></form:label>
                <div class="col-md-4">
                    <form:input path="secondName" id="secondName" name="secondName" type="text" placeholder="" class="form-control input-md" required="" />
                    <form:errors path="secondName" cssClass="alert alert-danger" />
                </div>
            </div>
            <!-- Text input-->
            <div class="form-group">
                <form:label path="email" class="col-md-4 control-label" for="email"><spring:message code="login.email" /></form:label>
                <div class="col-md-4">
                    <form:input path="email" id="email" name="email" type="email" placeholder="" class="form-control input-md" required="" />
                    <form:errors path="email" cssClass="alert alert-danger" />
                </div>
            </div>
            <!-- Password input-->
            <div class="form-group">
                <form:label path="password" class="col-md-4 control-label" for="password"><spring:message code="login.password" /></form:label>
                <div class="col-md-4">
                    <form:input path="password" id="password" name="password" type="password" placeholder="" class="form-control input-md" required="" />
                </div>
            </div>
            <div class="form-group">
                <form:label path="male" class="col-md-4 control-label" for="selectbasic"><spring:message code="register.gender" /></form:label>
                <div class="col-md-4">
                    <form:select path="male" id="selectbasic" name="selectbasic" class="form-control">
                        <option value="1"><spring:message code="register.gender.male"/></option>
                        <option value="0"><spring:message code="register.gender.female" /></option>
                    </form:select>
                </div>
            </div>
            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="register"></label>
                <div class="col-md-4">
                    <form:button type="submit" id="register" name="register" class="btn btn-success"><spring:message code="login.register" /></form:button>
                </div>
            </div>
        </fieldset>
    </form:form>
</t:login>
