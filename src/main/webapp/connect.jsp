<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <form method="POST" class="form-horizontal" action="connect">
            <div class="form-group">
                <label for="inputUsername1" class="col-sm-2 control-label">Username</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="username" id="inputUsername1" placeholder="Username" required autofocus>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword1" class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" name="password" id="inputPassword1" placeholder="Password" required>
                </div>
            </div>
            <div class="form-group">
                <label for="inputURL1" class="col-sm-2 control-label">URL</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="url" id="inputURL1" placeholder="URL" required>
                </div>
            </div>
            <div class="form-group">
                <label for="inputPort1" class="col-sm-2 control-label">Port</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="port" id="inputPort1" placeholder="Port" required>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Connect</button>
                </div>
            </div>
        </form>
        ${problem}
    </jsp:body>
</t:template>
