<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:login>
    <form class="form-horizontal">
        <fieldset>
            <!-- Form Name -->
            <legend><spring:message code="register.panel" /></legend>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="firstName"><spring:message code="register.firstname" /></label>
                <div class="col-md-4">
                    <input id="firstName" name="firstName" type="text" placeholder="" class="form-control input-md" required="">
                </div>
            </div>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="secondName"><spring:message code="register.secondname" /></label>
                <div class="col-md-4">
                    <input id="secondName" name="secondName" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>
            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="email"><spring:message code="login.email" /></label>
                <div class="col-md-4">
                    <input id="email" name="email" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>
            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password"><spring:message code="login.password" /></label>
                <div class="col-md-4">
                    <input id="password" name="password" type="password" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="gender"><spring:message code="register.gender" /></label>
                <div class="col-md-4">
                    <div class="radio">
                        <label for="gender-0">
                            <input type="radio" name="gender" id="gender-0" value="1" checked="checked">
                            <spring:message code="register.gender.male" />
                        </label>
                    </div>
                    <div class="radio">
                        <label for="gender-1">
                            <input type="radio" name="gender" id="gender-1" value="0">
                            <spring:message code="register.gender.female" />
                        </label>
                    </div>
                </div>
            </div>
            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="register"></label>
                <div class="col-md-4">
                    <button id="register" name="register" class="btn btn-success"><spring:message code="login.register" /></button>
                </div>
            </div>
        </fieldset>
    </form>
</t:login>
