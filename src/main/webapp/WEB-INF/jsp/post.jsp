<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:url value="/home/post/comment/" var="commentFormURL"/>

<t:genericpage title="Post">
    <t:post post="${post}" show="true"/>
</t:genericpage>