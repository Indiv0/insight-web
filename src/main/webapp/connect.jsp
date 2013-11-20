<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <form method="POST" class="form-signin" action="connect">
            <h2 class="form-signin-heading">Please sign in</h2>
            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <input type="text" class="form-control" name="url" placeholder="URL" required>
            <input type="text" class="form-control" name="port" placeholder="Port" required>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
            </label>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
        </form>
        ${problem}
    </jsp:body>
</t:template>
