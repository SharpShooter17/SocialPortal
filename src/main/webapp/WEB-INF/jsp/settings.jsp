<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage title="Settings">
    <div class="row justify-content-md-center">
        <div class="col-md-4 p-4 bg-white">
            <%--@elvariable id="userSettings" type="com.ujazdowski.SocialPortal.model.forms.SettingsForm"--%>
            <form:form action="/home/settings/" method="post" modelAttribute="userSettings">
                <form:label path="firstName" for="firstName"><spring:message code="register.firstname"/></form:label>
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </div>
                    <form:input path="firstName" id="firstName" name="firstName" type="text" required="required" cssClass="form-control here"/>
                    <form:errors path="firstName" cssErrorClass="text-danger" cssClass="alert alert-danger"/>
                </div>

                <form:label path="secondName" value="${user.secondName}" for="secondName"><spring:message code="register.secondname"/></form:label>
                <div class="input-group">
                    <div class="input-group-addon">
                        <i class="fa fa-user"></i>
                    </div>
                    <form:input path="secondName" id="secondName" name="secondName" type="text" required="required"
                                cssClass="form-control here"/>
                    <form:errors path="secondName" cssClass="alert alert-danger"/>
                </div>
                <form:label path="language" value="${user.language}" for="lang"><spring:message code="refister.preferredlanguage"/></form:label>
                <div class="input-group">
                    <form:select path="language" id="lang" name="lang" class="custom-select">
                        <c:forEach items="${languages}" var="lang">
                            <form:option value="${lang}">${lang.name}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group mt-3">
                    <form:button name="submit" type="submit" class="btn btn-primary"><spring:message code="settings.save"/></form:button>
                </div>
            </form:form>
        </div>
    </div>
</t:genericpage>