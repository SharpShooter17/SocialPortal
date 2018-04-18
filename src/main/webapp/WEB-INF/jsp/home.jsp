<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<t:genericpage title="Home">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <c:forEach items="${posts.getContent()}" var="post">
                <t:post post="${post}" show="false" />
            </c:forEach>
            <t:paginator totalPages="${totalPages}" baseLink="home" onPage="${onPage}" />
        </div>
    </div>
</t:genericpage>