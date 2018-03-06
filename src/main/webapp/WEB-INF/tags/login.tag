<%--
  Created by IntelliJ IDEA.
  User: Bartosz Ujazdowski
  Date: 06.03.2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
    <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    <title>Login - Social portal</title>
    <link rel="stylesheet" type="text/css" media="screen" href="http://tarruda.github.com/bootstrap-datetimepicker/assets/css/bootstrap-datetimepicker.min.css">
</head>
<body>
    <div id="body" class="container">
        <jsp:doBody/>
    </div>
</body>
</html>