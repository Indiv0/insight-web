<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:body>
        <form method="POST" class="form-signin" action="configure">
            <h2 class="form-signin-heading">Please Enter Configuration</h2>
            <input type="text" class="form-control" name="username" placeholder="Username" required autofocus> (Currently: ${dbUsername})
            <input type="password" class="form-control" name="password" placeholder="Password" required>
            <input type="text" class="form-control" name="url" placeholder="URL" required> (Currently: ${dbUsername})
            <input type="text" class="form-control" name="port" placeholder="Port" required> (Currently: ${dbUsername})
            <input type="text" class="form-control" name="name" placeholder="Database Name" required> (Currently: ${dbName})
            <button class="btn btn-lg btn-primary btn-block" type="submit">Configure</button>
        </form>
        ${problem}
    </jsp:body>
</t:template>
