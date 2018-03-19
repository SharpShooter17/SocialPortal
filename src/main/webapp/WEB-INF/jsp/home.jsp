<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<t:genericpage title="Home">
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
                <a class="page-link" href='<c:url value="/home/${onPage-1}"/>'>Previous</a>
            </li>
            <c:forEach begin="0" end="${totalPages-1 < 0 ? 0 : totalPages-1}" step="1" var="index">
                <li class="page-item <c:if test="${index == onPage}">active</c:if>">
                    <a class="page-link" href='<c:url value="/home/${index}"/>'>${index}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href='<c:url value="/home/${onPage+1}"/>'>Next</a>
            </li>
        </ul>
    </c:if>
</t:genericpage>