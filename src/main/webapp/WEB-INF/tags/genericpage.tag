<%@tag description="Social portal template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="title"%>

<security:authorize access="isAuthenticated()">
    <html>
    <head>
        <link href="<c:url value="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet" />
        <title>${title} - Social Portal</title>
    </head>
    <body>
        <jsp:include page="../jsp/header.jsp" />
        <div id="body" class="container-fluid">
            <jsp:doBody/>
        </div>
        <script src='<c:url value="/webjars/jquery/1.9.1/jquery.min.js"/>'></script>
        <script src='<c:url value="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"/>'></script>
    </body>
    </html>
</security:authorize>
