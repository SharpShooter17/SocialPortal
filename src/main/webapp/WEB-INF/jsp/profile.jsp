<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<security:authentication var="logged" property="principal.user" />

<t:genericpage title="Profile">
    <div >
        <div class="row">
            <div class="col-md-12">
                <h1><spring:message code="profile.user" />: ${user.firstName} ${user.secondName}</h1>
                <hr />
            </div>
        </div>
        <div class="row">
            <div class="col-sm-offset-1 col-md-4">
                <div class="row">
                    <div class="col">
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
                        <c:if test="${logged.userId != user.userId}">
                            <li class="list-group-item">
                                <c:choose>
                                    <c:when test="${empty areFriends.fromUser}">
                                        <%--@elvariable id="invitationForm" type="com.ujazdowski.SocialPortal.model.forms.InvitationForm"--%>
                                        <form:form action="/home/profile/${user.userId}" method="post" modelAttribute="invitationForm">
                                            <form:input path="fromUser" type="hidden" name="fromUser" value="${logged.userId}"/>
                                            <form:input path="toUser" type="hidden" name="toUser" value="${user.userId}"/>
                                            <form:hidden path="accepted" name="accepted" value="0"/>
                                            <form:button id="sendInvitation" type="submit" class="btn btn-primary btn-lg"><spring:message code="profile.addToFriends" /></form:button>
                                        </form:form>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${areFriends.accepted == true}">
                                                <spring:message code="profile.wearefriends" /> : ${areFriends.sended}
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${areFriends.fromUser.userId == logged.userId}">
                                                        <spring:message code="profile.invitationHasSended" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <%--@elvariable id="areFriends" type="com.ujazdowski.SocialPortal.model.tables.Invitation"--%>
                                                        <form:form method="post" modelAttribute="areFriends" action="/home/profile/${user.userId}">
                                                            <form:hidden path="fromUser" name="fromUser" value="${logged.userId}"/>
                                                            <form:hidden path="toUser" name="toUser" value="${user.userId}"/>
                                                            <form:hidden path="accepted" name="accepted" value="1"/>
                                                            <form:button type="submit" class="btn btn-success btn-block"><spring:message code="profile.acceptInvitation" /></form:button>
                                                        </form:form>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </c:if>
                    </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <h2><spring:message code="profile.friends"/></h2>
                        <hr />
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th><spring:message code="profile.friends.name"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <c:forEach items="${friends}" var="friend">
                                        <tr>
                                            <td>
                                                <c:out value="${friend.firstName}" /> <c:out value="${friend.secondName}" />
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-sm-offset-1 col-md-5">
                <h3><spring:message code="profile.posts"/></h3>
                <hr />
                <c:forEach items="${posts}" var="post">
                    <div class="row">
                        <div class="row">
                            <div class="col-md-4">
                                <c:out value="${post.user.firstName}"/> <c:out value="${post.user.secondName}"/>
                            </div>
                            <div class="col-md-offset-5 col-md-3">
                                <c:out value="${post.date}"/>
                            </div>
                            <hr />
                        </div>
                        <div class="col">
                            <c:out value="${post.text}"/>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</t:genericpage>