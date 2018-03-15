<%@tag description="Social portal template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="title"%>

<security:authorize access="isAuthenticated()">
    <html>
    <head>
        <%--<link href="<c:url value="/webjars/bootstrap/4.0.0/css/bootstrap.min.css" />" type="text/css" rel="stylesheet" />--%>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" />
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous" />
        <title>${title} - Social Portal</title>
    </head>
    <body>
        <jsp:include page="../jsp/header.jsp" />
        <div id="body" class="container-fluid">
            <div class="row">
                <jsp:doBody/>
            </div>
        </div>
        <script type="application/javascript" src='<c:url value="/webjars/jquery/3.0.0/jquery.min.js"/>'></script>
        <script type="application/javascript" src='<c:url value="/webjars/bootstrap/4.0.0/js/bootstrap.min.js"/>'></script>
    </body>
    </html>
</security:authorize>
