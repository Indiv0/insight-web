<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:attribute name="script">
        <script src="https://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
        <script src="public/js/hash.js"></script>
    </jsp:attribute>
    <jsp:body>
        <h1>Settings</h1>
        <c:choose>
            <c:when test="${not empty info}">
                ${info}

                <br/><br/>
                <a class="btn btn-lg btn-primary" href="settings" role="button">Return</a>
            </c:when>
            <c:otherwise>
                <form method="POST" class="form-signin" action="settings?changePassword" onsubmit="hashPassword('changePassword', 'password');" name="changePassword">
                    <h2 class="form-signin-heading">Change Password</h2>
                    <input type="password" class="form-control" name="password" placeholder="Password" required autofocus>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Change</button>
                </form>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:template>
