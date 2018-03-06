<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:login>
    <div class="row">
    <form class="form-horizontal" method="post">
        <fieldset>
            <legend><spring:message code="login.panel" /></legend>
            <div class="form-group">
              <label class="col-md-4 control-label" for="signInInput"><spring:message code="login.email" /></label>
              <div class="col-md-4">
                <input id="signInInput" name="signInInput" type="text" class="form-control input-md" required="">
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label" for="passwordinput"><spring:message code="login.password" /></label>
              <div class="col-md-4">
                <input id="passwordinput" name="passwordinput" type="password" class="form-control input-md" required="">
              </div>
            </div>
            <div class="form-group">
              <label class="col-md-4 control-label" for="signIn"></label>
              <div class="col-md-4">
                <button style="width: 100%" id="signIn" name="signIn" class="btn btn-success"><spring:message code="login.signin" /></button>
              </div>
            </div>
            <div class="form-group">
                <label class="col-md-4 control-label"></label>
                <div class="col-md-4">
                    <a href="Register"><spring:message code="login.noAccount" /></a>
                </div>
            </div>
        </fieldset>
    </form>
    </div>
</t:login>