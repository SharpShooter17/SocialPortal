<%@tag description="Social portal template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="post" required="true" type="com.ujazdowski.SocialPortal.model.tables.Post" %>
<%@attribute name="show" required="true"%>

<c:set var="canDelete" value="false" />

<security:authentication var="logged" property="principal.user"/>

<security:authorize access="hasRole('ROLE_ADMINISTRATOR')">
    <c:set var="canDelete" value="true" />
</security:authorize>
<c:if test="${logged.userId == post.user.userId}">
    <c:set var="canDelete" value="true" />
</c:if>

<c:url value="/home/post/comment/" var="commentFormURL"/>

<c:set value="" var="showComments" />
<c:if test="${show == true}">
    <c:set value="show" var="showComments" />
</c:if>

<div class="card mb-3">
    <div class="card-header">
        <a href="/home/profile/${post.user.userId}">
            <c:choose>
                <c:when test="${empty user.profile}">
                    <i class="rounded-circle fas fa-user fa-10x" style="width: 30px; height: 30px;"></i>
                </c:when>
                <c:otherwise>
                    <img src="/file/user/pictrue/${post.user.profile}" style="width: 30px; height: 30px;" class="rounded-circle" />
                </c:otherwise>
            </c:choose>
            ${post.user.firstName} ${post.user.secondName} - ${post.date}
        </a>
        <c:if test="${canDelete == true}" >
            <a href="/home/post/delete/${post.postId}">
                <button type="button" class="close" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </a>
        </c:if>
    </div>
    <div class="card-body lead">
        <p class="card-text"><c:out value="${post.text}"/></p>
    </div>
    <form action="${commentFormURL}" method="post" class="m-3">
        <div class="form-group">
            <input type="hidden" name="postId" value="${post.postId}">
            <textarea id="comment" name="comment" cols="40" rows="2" class="form-control" required="required"></textarea>
        </div>
        <div class="form-group">
            <button name="submit" type="submit" class="btn btn-primary"><spring:message code="profile.post.addComment"/></button>
        </div>
    </form>
    <div class="accordion">
        <button class="btn btn-link" data-toggle="collapse" data-target="#Post${post.postId}" aria-expanded="true" aria-controls="collapseOne">
           pokaż komentarze
        </button>
        <div id="Post${post.postId}" class="collapse ${showComments}" aria-labelledby="headingOne" data-parent="#accordion">
            <c:forEach items="${post.comments}" var="comment">
                <div class="card m-3">
                    <div class="card-header">${comment.user.firstName} ${comment.user.secondName} - ${comment.date}</div>
                    <div class="card-body lead">
                        <p class="card-text"><c:out value="${comment.comment}"/></p>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
