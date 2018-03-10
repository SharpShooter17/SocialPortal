<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<t:genericpage userId="${user.userId}" secondName="${user.secondName}" firstName="${user.firstName}" title="Profile">
    <div>
        <div class="row">
            <div class="col">
                <h1><spring:message code="profile.user" />: ${user.firstName}</h1>
            </div>
        </div>
    </div>
</t:genericpage>