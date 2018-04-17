<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.ujazdowski.SocialPortal.service.CustomUser"%>

<security:authentication var="user" property="principal.user" />

<nav class="navbar navbar-expand-lg navbar-dark bg-dark pl-5 pr-5">
    <a class="navbar-brand" href="/home">Social Portal</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <%--@elvariable id="searchForm" type="com.ujazdowski.SocialPortal.model.forms.SearchForm"--%>
        <form action="/home/search" method="get" class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" name="search" type="search" placeholder="Search" aria-label="Search" />
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><spring:message code="search"/></button>
        </form>
        <div class="collapse navbar-collapse justify-content-end mr-5">
            <ul class="navbar-nav">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownNotifications" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-bell"></i>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <c:forEach items="${user.invitationsNotifications}" var="notification">
                            <a class="dropdown-item" href="/home/profile/${notification.invitation.fromUser.userId}">
                                Invitation: ${notification.invitation.fromUser.firstName} ${notification.invitation.fromUser.secondName}
                            </a>
                        </c:forEach>
                    </div>
                </li>
                <li class="nav-item active">
                    <a class="nav-link" href="/home/profile/${user.userId}"><span>${user.firstName} ${user.secondName}</span></a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <spring:message code="nav.settings" />
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="/home/settings"><spring:message code="nav.settings.account"/></a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="/logout"><spring:message code="nav.settings.logout"/></a>
                    </div>
                </li>
            </ul>
        </div>

    </div>
</nav>