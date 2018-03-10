<%@tag description="Social portal template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="title"%>
<%@attribute name="firstName"%>
<%@attribute name="secondName"%>
<%@attribute name="userId"%>

<html>
<head>
    <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet" />
    <title>${title} - Social Portal</title>
</head>
<body>
    <jsp:include page="../jsp/header.jsp">
        <jsp:param name="firstName" value="${firstName}" />
        <jsp:param name="secondName" value="${secondName}" />
        <jsp:param name="userId" value="${userId}" />
    </jsp:include>
    <div id="body">
        <jsp:doBody/>
    </div>
    <script src='<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>'></script>
    <script src='<c:url value="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"/>'></script>
</body>
</html>
