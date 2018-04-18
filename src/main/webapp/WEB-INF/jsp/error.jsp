<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<c:set var="url" value="/" />

<security:authorize access="isAuthenticated()">
    <c:set var="url" value="/home" />
</security:authorize>

<t:login title="Error">
    <div class="error-wall justify-content-md-center load-error bg-white p-4">
        <div class="error-container">
            <h1>oh no...</h1>
            <h3>We have had an error</h3>
            <h4>Error ${status} ${error}</h4>
            <p>${timestamp}</p>
            <h5>Message</h5>
            <p>${message}</p>
            <h6>${exception}</h6>
            <div class="error-actions">
                <a href="${url}" class="btn btn-primary btn-lg">
                    <i class="fas fa-home"></i> Take Me Home
                </a>
                <a href="mailto:support@socialportal.com" class="btn btn-default btn-lg"><i class="fas fa-envelope"></i> Contact Support </a>
            </div>
        </div>
    </div>
</t:login>