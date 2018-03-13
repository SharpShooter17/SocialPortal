<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<security:authentication var="logged" property="principal.user" />

<t:genericpage title="Profile">
    <div>
        <div class="row">
            <div class="col-md-12">
                <h1><spring:message code="profile.user" />: ${user.firstName} ${user.secondName}</h1>
                <hr />
            </div>
        </div>
        <div class="roe">
            <div class="col-md-4">
                <ul list-group list-group-flush>
                    <li class="list-group-item"><spring:message code="profile.email" />: ${user.email}</li>
                    <li class="list-group-item"><spring:message code="profile.lastTimeOnline" />: ${user.lastTimeOnline}</li>
                    <li class="list-group-item"><spring:message code="profile.gender" />:
                        <c:choose>
                            <c:when test="${user.male == true}">
                                <spring:message code="profile.gender.male" />
                            </c:when>
                            <c:otherwise>
                                <spring:message code="profile.gender.female" />
                            </c:otherwise>
                        </c:choose>
                    </li>
                    <li class="list-group-item">
                        <c:choose>
                            <c:when test="${empty friends.fromUser}">
                                <%--@elvariable id="friends" type="com.ujazdowski.SocialPortal.model.tables.Invitation"--%>
                                <form:form name="f" method="post" modelAttribute="friends">
                                    <form:input path="fromUser" type="hidden" name="fromUser" value="${logged}"/>
                                    <form:input path="toUser" type="hidden" name="toUser" value="${user}"/>
                                    <form:button id="sendInvitation" type="submit" class="btn btn-primary btn-lg"><spring:message code="profile.addToFriends" /></form:button>
                                </form:form>
                            </c:when>
                            <c:otherwise>
                                <c:when test="${friends.accepted == true}">
                                    Znamy się od: ${friends.sended}
                                </c:when>
                                <c:otherwise>
                                    <c:when test="${friends.fromUser == logged.userId}">
                                        Wysłano zaproszenie
                                    </c:when>
                                    <c:otherwise>
                                        button - akceptuj
                                    </c:otherwise>
                                </c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</t:genericpage>