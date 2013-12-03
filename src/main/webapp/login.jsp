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
        <form method="POST" class="form-horizontal" action="j_security_check" onsubmit="hashPassword('login', 'j_password');" name="login">
            <h1 class="form-signin-heading">Please Sign In</h1>
            <div class="form-group">
                <label for="inputUsername1" class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="j_username" id="inputUsername1" placeholder="Username" required autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="j_password" id="inputPassword1" placeholder="Password" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label>
                          <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign in</button>
                </div>
            </div>
        </form>

        <c:if test="${not empty status}">
            <c:if test="${status == 0}">
                <p>Login Failed.</p>
            </c:if>
        </c:if>
    </jsp:body>
</t:template>
