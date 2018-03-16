<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<security:authentication var="logged" property="principal.user"/>

<t:genericpage title="Profile">
    <div class="row">
        <div class="col-sm-offset-1 col-md-4">
            <div class="row bg-white m-2 mb-3 p-2">
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${empty user.profile}">
                            <i class="fas fa-user fa-10x"></i>
                        </c:when>
                        <c:otherwise>
                            <img class="img-thumbnail" src="/file/user/pictrue/${user.profile}" />
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><spring:message code="profile.user"/>:
                            <b>${user.firstName} ${user.secondName}</b></li>
                        <li class="list-group-item"><spring:message code="profile.email"/>: ${user.email}</li>
                        <li class="list-group-item"><spring:message code="profile.gender"/>:
                            <c:choose>
                                <c:when test="${user.male == true}">
                                    <div class="d-inline input-group">
                                        <div class="d-inline input-group-addon">
                                            <i class="fa fa-mars"></i>
                                        </div>
                                        <spring:message code="profile.gender.male"/>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="d-inline input-group">
                                        <div class="d-inline input-group-addon">
                                            <i class="fa fa-venus"></i>
                                        </div>
                                        <spring:message code="profile.gender.female"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </li>
                        <c:if test="${logged.userId != user.userId}">
                            <li class="list-group-item">
                                <c:choose>
                                    <c:when test="${empty areFriends.fromUser}">
                                        <%--@elvariable id="invitationForm" type="com.ujazdowski.SocialPortal.model.forms.InvitationForm"--%>
                                        <form:form action="/home/profile/${user.userId}" method="post"
                                                   modelAttribute="invitationForm">
                                            <form:input path="fromUser" type="hidden" name="fromUser"
                                                        value="${logged.userId}"/>
                                            <form:input path="toUser" type="hidden" name="toUser"
                                                        value="${user.userId}"/>
                                            <form:hidden path="accepted" name="accepted" value="0"/>
                                            <form:button id="sendInvitation" type="submit"
                                                         class="btn btn-outline-success btn-block"><spring:message
                                                    code="profile.addToFriends"/></form:button>
                                        </form:form>
                                    </c:when>
                                    <c:otherwise>
                                        <c:choose>
                                            <c:when test="${areFriends.accepted == true}">
                                                <spring:message code="profile.wearefriends"/> : ${areFriends.sended}
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${areFriends.fromUser.userId == logged.userId}">
                                                        <spring:message code="profile.invitationHasSended"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <%--@elvariable id="areFriends" type="com.ujazdowski.SocialPortal.model.tables.Invitation"--%>
                                                        <form:form method="post" modelAttribute="areFriends"
                                                                   action="/home/profile/${user.userId}">
                                                            <form:hidden path="fromUser" name="fromUser"
                                                                         value="${logged.userId}"/>
                                                            <form:hidden path="toUser" name="toUser"
                                                                         value="${user.userId}"/>
                                                            <form:hidden path="accepted" name="accepted" value="1"/>
                                                            <form:button type="submit"
                                                                         class="btn btn-success btn-block"><spring:message
                                                                    code="profile.acceptInvitation"/></form:button>
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
                    <hr/>
                    <div class="table-responsive">
                        <table class="table table-striped">
                            <thead class="thead-light">
                            <tr>
                                <th><spring:message code="profile.friends.name"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <c:forEach items="${friends}" var="friend">
                            <tr>
                                <td>
                                    <a href='<c:url value="/home/profile/${friend.userId}"/>'><c:out
                                            value="${friend.firstName}"/> <c:out value="${friend.secondName}"/></a>
                                </td>
                            </tr>
                            </c:forEach>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div class="col m-2">
            <c:if test="${logged.userId == user.userId}">
                <%--@elvariable id="newPost" type="com.ujazdowski.SocialPortal.model.forms.PostForm"--%>
                <form:form method="post" action="/home/post" class="bg-white p-3" modelAttribute="newPost">
                    <div class="form-group">
                        <form:label path="text" for="post">Co słychać?</form:label>
                        <form:textarea path="text" id="post" name="post" cols="40" rows="5" required="required"
                                       class="form-control"></form:textarea>
                        <form:hidden path="userId" value="${logged.userId}"/>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-primary">Opublikuj</button>
                    </div>
                </form:form>
            </c:if>
            <c:forEach items="${posts.getContent()}" var="post">
                <div class="card mb-3">
                    <div class="card-header">${post.user.firstName} ${post.user.secondName} - ${post.date}</div>
                    <div class="card-body lead">
                        <p class="card-text"><c:out value="${post.text}"/></p>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${totalPages-1 > 0}">
                <ul class="pagination">
                    <li class="page-item">
                        <a class="page-link" href='<c:url value="/home/profile/${user.userId}/${onPage-1}"/>'>Previous</a>
                    </li>
                    <c:forEach begin="0" end="${totalPages-1 < 0 ? 0 : totalPages-1}" step="1" var="index">
                        <li class="page-item <c:if test="${index == onPage}">active</c:if>">
                            <a class="page-link" href='<c:url value="/home/profile/${user.userId}/${index}"/>'>${index}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href='<c:url value="/home/profile/${user.userId}/${onPage+1}"/>'>Next</a>
                    </li>
                </ul>
            </c:if>
        </div>
    </div>
</t:genericpage>