<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:url value="/home/post/comment/" var="commentFormURL"/>

<t:genericpage title="Post">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <t:post post="${post}" show="true"/>
        </div>
    </div>
</t:genericpage>