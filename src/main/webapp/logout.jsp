<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <div class="jumbotron">
            <h1>Logout</h1>
            <%@ page session="true"%>
            User "${pageContext.request.remoteUser}" has been logged out.

            <br/><br/>
            <a class="btn btn-lg btn-primary" href=".." role="button">Return</a>
        </div>
    </jsp:body>
</t:template>


