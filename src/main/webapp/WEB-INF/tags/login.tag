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
    <%--<link rel="stylesheet" type="text/css" href="webjars/bootstrap/4.0.0-2/css/bootstrap.min.css" />--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous" />

        <c:url value="/css/main.css" var="jstlCss" />
    <link href="${jstlCss}" rel="stylesheet" />
    <title>Login - Social portal</title>
</head>
<body>
    <div id="body" class="container">
        <jsp:doBody/>
    </div>
</body>
</html>