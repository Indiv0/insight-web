<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <!-- Main component for a primary marketing message or call to action -->
        <div class="jumbotron">
            <h1>Insight Administration</h1>
            <p>This site allows you to administrate the Insight database.</p>
            <p>
            <a class="btn btn-lg btn-primary" href="connect" role="button">Connect SQL &raquo;</a>
            </p>
            <h1>SQL Status</h1>
            <p>Connected: ${connected}</p>
            ${connectionInfo}
        </div>
    </jsp:body>
</t:template>

