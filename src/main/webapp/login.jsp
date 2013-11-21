<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:attribute name="script">
        <script src="https://crypto-js.googlecode.com/svn/tags/3.1.2/build/rollups/md5.js"></script>
        <script src="js/hash.js"></script>
    </jsp:attribute>
    <jsp:body>
        <form method="POST" class="form-signin" action="j_security_check" onsubmit="hashPassword();" name="login">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="j_username" placeholder="Username" required autofocus>
            <input type="password" class="form-control" name="j_password" placeholder="Password" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
        </form>
        <p>${status}</p>
        <p>${admin}</p>
        <p>${table}</p>
        <p>${auth}</p>
        <p>${name}</p>
        <p>${role}</p>
    </jsp:body>
</t:template>