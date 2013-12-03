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
        <div class="page-header">
            <h1>User Administration</h1>
        </div>

        <h2>Users List</h2>
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

        <h2>Roles List</h2>
        <c:choose>
            <c:when test="${not empty roles}">
                <c:set var="count" value="0" scope="page" />
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Rolename</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${roles}" var="element">
                            <c:set var="count" value="${count + 1}" scope="page"/>
                            <tr>
                                <th>${count}</th>
                                <td>${element}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
        </c:choose>

        <h2>User Actions</h2>
        <c:choose>
            <c:when test="${not empty registrationInfo}">
                ${registrationInfo}

                <br/><br/>
                <a class="btn btn-lg btn-primary" href="users" role="button">Return</a>
            </c:when>
            <c:otherwise>
                <form method="POST" class="form-horizontal" action="users?registerUser" onsubmit="hashPassword('registerUser', 'password');" name="registerUser">
                    <h3 class="form-signin-heading">Register a New User</h3>
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
                        <label for="inputRolename1" class="col-sm-2 control-label">Rolename</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="rolename" id="inputRolename1" placeholder="Rolename">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Register</button>
                        </div>
                    </div>
                </form>

                <form method="POST" class="form-horizontal" action="users?deleteUser" name="deleteUser">
                    <h3 class="form-signin-heading">Delete a User</h3>
                    <div class="form-group">
                        <label for="inputUsername2" class="col-sm-2 control-label">Username</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="username" id="inputUsername2" placeholder="Username" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Delete</button>
                        </div>
                    </div>
                </form>

                <form method="POST" class="form-horizontal" action="users?registerRole" name="addRole">
                    <h3 class="form-signin-heading">Add a New Role</h3>
                    <div class="form-group">
                        <label for="inputRolename2" class="col-sm-2 control-label">Rolename</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="rolename" id="inputRolename2" placeholder="Rolename" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Add</button>
                        </div>
                    </div>
                </form>

                <form method="POST" class="form-horizontal" action="users?deleteRole" name="deleteRole">
                    <h3 class="form-signin-heading">Delete a Role</h3>
                    <div class="form-group">
                        <label for="inputRolename3" class="col-sm-2 control-label">Rolename</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" name="rolename" id="inputRolename3" placeholder="Rolename" required autofocus>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Delete</button>
                        </div>
                    </div>
                </form>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:template>
