<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
	<c:url value="/css/main.css" var="jstlCss" />
	<link href="${jstlCss}" rel="stylesheet" />
</head>
<body>
	<div class="container">
		<div class="starter-template">
			<form class="form-horizontal">
            <fieldset>

            <!-- Form Name -->
            <legend><spring:message code="${login.signn}" /></legend>

            <!-- Text input-->
            <div class="form-group">
              <label class="col-md-4 control-label" for="signInInput">email</label>
              <div class="col-md-4">
              <input id="signInInput" name="signInInput" type="text" placeholder="email" class="form-control input-md" required="">

              </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
              <label class="col-md-4 control-label" for="passwordinput">Password</label>
              <div class="col-md-4">
                <input id="passwordinput" name="passwordinput" type="password" placeholder="password" class="form-control input-md" required="">

              </div>
            </div>

            <!-- Button (Double) -->
            <div class="form-group">
              <label class="col-md-4 control-label" for="signIn"></label>
              <div class="col-md-8">
                <button id="signIn" name="signIn" class="btn btn-success"></button>
                <button id="register" name="register" class="btn btn-danger">Register</button>
              </div>
            </div>

            </fieldset>
            </form>
		</div>
	</div>
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>