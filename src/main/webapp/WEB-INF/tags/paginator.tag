<%@tag description="Social portal template" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@attribute name="totalPages" required="true"%>
<%@attribute name="baseLink" required="true"%>
<%@attribute name="onPage" required="true"%>

<c:if test="${totalPages-1 > 0}">
    <ul class="pagination">
        <li class="page-item <c:if test="${onPage == 0}">disabled</c:if>">
            <a class="page-link" href='<c:url value="/${baseLink}/${onPage-1}"/>'><spring:message code="page.previous" /></a>
        </li>
        <c:forEach begin="0" end="${totalPages-1 < 0 ? 0 : totalPages-1}" step="1" var="index">
            <li class="page-item <c:if test="${index == onPage}">active</c:if>">
                <a class="page-link" href='<c:url value="/${baseLink}/${index}"/>'>${index+1}</a>
            </li>
        </c:forEach>
        <li class="page-item <c:if test="${onPage+1 == totalPages}">disabled</c:if>">
            <a class="page-link" href='<c:url value="/home/${onPage+1}"/>'><spring:message code="page.next" /></a>
        </li>
    </ul>
</c:if>