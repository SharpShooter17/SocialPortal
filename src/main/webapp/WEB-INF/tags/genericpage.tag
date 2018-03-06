<%--
  Created by IntelliJ IDEA.
  User: Bartosz Ujazdowski
  Date: 06.03.2018
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div id="pageheader">
        <jsp:invoke fragment="header"/>
    </div>
    <div id="body">
        <jsp:doBody/>
    </div>
    <div id="pagefooter">
        <jsp:invoke fragment="footer"/>
    </div>

    <script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
