<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<security:authentication var="logged" property="principal.user" />

<t:genericpage title="Profile">
    <div class="row">
        <div class="col-md-12">
            <h1><spring:message code="profile.user" />: ${user.firstName} ${user.secondName}</h1>
            <hr />
        </div>
    </div>
    <div class="row">
        <div class="col-sm-offset-1 col-md-4">
            <div class="row bg-white m-2 mb-3 p-2">
                <div class="col">
                <ul class="list-group list-group-flush">
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
            <div class="row bg-white m-2 mb-3 p-2">
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
        <div class="col m-2">
            <c:if test="${logged.userId == user.userId}">
                <%--@elvariable id="newPost" type="com.ujazdowski.SocialPortal.model.forms.PostForm"--%>
                <form:form method="post" action="/home/post" class="bg-white p-3" modelAttribute="newPost">
                    <div class="form-group">
                        <form:label path="text" for="post">Co słychać?</form:label>
                        <form:textarea path="text" id="post" name="post" cols="40" rows="5" required="required" class="form-control"></form:textarea>
                        <form:hidden path="userId" value="${logged.userId}" />
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Opublikuj</button>
                    </div>
                </form:form>
            </c:if>
            <c:forEach items="${posts}" var="post">
                <div class="card mb-3">
                    <div class="card-header">${post.user.firstName} ${post.user.secondName} - ${post.date}</div>
                    <div class="card-body blockquote">
                        <p class="card-text"><c:out value="${post.text}"/></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</t:genericpage>