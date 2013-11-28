<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:attribute name="script">
        <script src="https://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
        <script src="js/hash.js"></script>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" class="form-signin" action="j_security_check" onsubmit="hashPassword('login', 'j_password');" name="login">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Username" required autofocus>
            <input type="password" class="form-control" name="j_password" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
        </form>
        <c:if test="${not empty status}">
            <c:if test="${status == 0}">
                <p>Login Failed.</p>
            </c:if>
        </c:if>
    </jsp:body>
</t:template>
