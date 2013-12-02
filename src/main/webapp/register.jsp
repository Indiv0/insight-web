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
        <h1>User Administration</h1>
        <c:choose>
            <c:when test="${not empty users}">
                <c:set var="count" value="0" scope="page" />
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Username</th>
                            <th>Rolename</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${users}" var="element">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <th>${count}</th>
                                <td>${element.getKey()}</td>
                                <td>${element.getValue()}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${not empty registrationInfo}">
                ${registrationInfo}

                <br/><br/>
                <a class="btn btn-lg btn-primary" href="register" role="button">Return</a>
            </c:when>
            <c:otherwise>
                <form method="POST" class="form-signin" action="register" onsubmit="hashPassword('register', 'password');" name="register">
                    <h2 class="form-signin-heading">Register a New Administrator</h2>
                    <input type="text" class="form-control" name="username" placeholder="Username" required autofocus>
                    <input type="password" class="form-control" name="password" placeholder="Password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Connect</button>
                </form>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:template>
