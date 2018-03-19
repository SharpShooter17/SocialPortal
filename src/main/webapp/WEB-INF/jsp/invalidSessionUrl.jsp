<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" var="lastTimeOnline" pattern="dd-MM-yyyy HH:mm:ss" />

<spring:message code="invalidSessionUrl.title" var="title" />

<t:login title="${title}">
    <div class="p-4 bg-white">
        <h1>${title}</h1>
        <p><spring:message code="invalidSessionUrl.descripion"/></p>
        <button type="submit" class="btn btn-primary"><spring:message code="login.signin" /></button>
    </div>
</t:login>