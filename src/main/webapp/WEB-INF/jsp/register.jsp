<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:message code="register.panel" var="registerPanel" />

<t:login title="${registerPanel}">
    <div class="row bg-white p-4">
        <%--@elvariable id="user" type="com.ujazdowski.SocialPortal.model.tables.User"--%>
        <form:form action="/Register" method="post" modelAttribute="user" cssClass="col-12">
            <div class="row">
                <div class="form-group col-md-6">
                    <form:label path="firstName" for="firstName"><spring:message code="register.firstname"/></form:label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-user"></i>
                        </div>
                        <form:input path="firstName" id="firstName" name="firstName" type="text" required="required"
                                    class="form-control here"/>
                        <form:errors path="firstName" cssErrorClass="text-danger" cssClass="alert alert-danger"/>
                    </div>
                </div>

                <div class="form-group col-md-6">
                    <form:label path="secondName" for="secondName"><spring:message code="register.secondname"/></form:label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-user"></i>
                        </div>
                        <form:input path="secondName" id="secondName" name="secondName" type="text" required="required"
                                    class="form-control here"/>
                        <form:errors path="secondName" cssClass="alert alert-danger"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <form:label path="email" for="email"><spring:message code="login.email"/></form:label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-at"></i>
                        </div>
                        <form:input path="email" id="email" name="email" type="text" required="required"
                                    class="form-control here"/>
                        <form:errors path="email" cssClass="alert alert-danger"/>
                    </div>
                </div>
                <div class="form-group col-md-6">
                    <form:label path="password" for="password"><spring:message code="login.password"/></form:label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <i class="fa fa-key"></i>
                        </div>
                        <form:input path="password" id="password" name="password" type="password" required="required"
                                    class="form-control here"/>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-6">
                    <form:label path="male" class="col-2">
                        <spring:message code="register.gender"/>
                    </form:label>
                    <div class="col-4">
                        <label class="custom-control custom-radio">
                            <form:radiobutton path="male" name="gender" class="custom-control-input" value="1"/>
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <i class="fa fa-venus"></i>
                                    </div>
                                    <spring:message code="register.gender.male"/>
                                </div>
                            </span>
                        </label>
                        <label class="custom-control custom-radio">
                            <form:radiobutton path="male" name="gender" class="custom-control-input" value="0"/>
                            <span class="custom-control-indicator"></span>
                            <span class="custom-control-description">
                                <div class="input-group">
                                    <div class="input-group-addon">
                                         <i class="fa fa-venus-mars"></i>
                                    </div>
                                    <spring:message code="register.gender.female"/>
                                </div>
                            </span>
                        </label>
                    </div>
                </div>

                <div class="form-group col-6">
                    <form:label path="language" for="lang"><spring:message code="refister.preferredlanguage"/></form:label>
                    <div class="input-group">
                        <form:select path="language" id="lang" name="lang" class="custom-select">
                            <c:forEach items="${languages}" var="lang">
                                <form:option value="${lang}">${lang.name}</form:option>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <form:button name="submit" type="submit" class="btn btn-primary"><spring:message
                        code="login.register"/></form:button>
            </div>
        </form:form>
    </div>
    <div class="row">
        <div class="col">
            <a href="/"><spring:message code="register.gobackttologinpanel"/></a>
        </div>
    </div>
</t:login>
