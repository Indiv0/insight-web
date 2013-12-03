<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <!-- Main component for a primary marketing message or call to action -->
        <div class="jumbotron">
            <h1>Insight Administration</h1>
            <p>This site allows you to administrate the Insight database.</p>
            <h2>SQL Status</h2>
            ${connectionInfo}
        </div>
    </jsp:body>
</t:template>

