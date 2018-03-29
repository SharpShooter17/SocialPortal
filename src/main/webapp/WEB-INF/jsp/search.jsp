<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage title="Search">
    <div class="row justify-content-md-center">
        <div class="col-md-8 p-4 bg-white">
            <h1><spring:message code="search.result"/>:</h1>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><spring:message code="profile.friends.name"/></th>
                    <th scope="col"><spring:message code="login.email"/></th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach items="${results}" var="result">
                        <tr>
                            <td>
                                <a href="/home/profile/${result.userId}">${result.firstName} ${result.secondName}</a>
                            </td>
                            <td>
                                <a href="/home/profile/${result.userId}">${result.email}</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</t:genericpage>