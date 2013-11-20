<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%
    request.setAttribute("dbName", Configuration.getProperty("dbName"));
    request.setAttribute("dbUsername", Configuration.getProperty("dbUsername"));
    request.setAttribute("dbURL", Configuration.getProperty("dbURL"));
    request.setAttribute("dbPort", Configuration.getProperty("dbPort"));
%>

<t:template>
    <jsp:body>
        <form method="POST" class="form-signin" action="configure">
            <h2 class="form-signin-heading">Please Enter Configuration</h2>
            <input type="text" class="form-control" name="username" placeholder="${dbUsername}" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <input type="text" class="form-control" name="url" placeholder="${dbURL}" required>
            <input type="text" class="form-control" name="port" placeholder="${dbPort}" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Configure</button>
        </form>
        ${problem}
    </jsp:body>
</t:template>
