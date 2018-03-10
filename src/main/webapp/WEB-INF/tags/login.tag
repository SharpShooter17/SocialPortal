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
</head>
<body>
    <div id="body" class="container">
        <jsp:doBody/>
    </div>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <SCRIPT src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></SCRIPT>
</body>
</html>