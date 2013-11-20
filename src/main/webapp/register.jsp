<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<%
    if ("true".equals(request.getAttribute("valid"))) {
        String registrationInfo = "";
        registrationInfo += "<h1>Registration Successful!</h1>";
        registrationInfo += "<p>" + request.getAttribute("username") + " was successfully registered.</p>";
        request.setAttribute("registrationInfo", registrationInfo);
    }
    request.setAttribute("valid", null);
%>

<t:template>
    <jsp:body>
        <form method="POST" class="form-signin" action="register">
            <h2 class="form-signin-heading">Please provide the information for the user you wish to register</h2>
            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
        </form>
        ${registrationInfo}
    </jsp:body>
</t:template>
