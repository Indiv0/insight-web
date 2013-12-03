<%@page import="in.nikitapek.insightweb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:template>
    <jsp:attribute name="script">
    </jsp:attribute>
    <jsp:body>
        <div class="page-header">
            <h1>Database Query</h1>
        </div>
        <c:choose>
            <c:when test="${connected}">
                <p>Please connect to the Insight logging database first.</p>
                <p><a class="btn btn-lg btn-primary" href="connect" role="button">Connect &raquo;</a></p>
            </c:when>
            <c:otherwise>
                <h2>Query Info</h2>
                <form method="POST" form class="form-inline" action="users?registerUser" role="form">
                    <div class="form-group">
                        <label class="sr-only" for="inputWorld1">World</label>
                        <input type="text" class="form-control" id="inputWorld1" placeholder="World" name="world">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputX1">X</label>
                        <input type="text" class="form-control" id="inputX1" placeholder="X" name="x">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputY1">Y</label>
                        <input type="text" class="form-control" id="inputY1" placeholder="Y" name="y">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputZ1">Z</label>
                        <input type="text" class="form-control" id="inputZ1" placeholder="Z" name="z">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="inputRadius1">Radius</label>
                        <input type="text" class="form-control" id="inputRadius1" placeholder="Radius" name="radius">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="inputPlayers1">Players</label>
                        <input type="text" class="form-control" id="inputPlayers1" placeholder="Players" name="players">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="inputEntities1">Entities</label>
                        <input type="text" class="form-control" id="inputEntities1" placeholder="Entities" name="entities">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="inputActions1">Actions</label>
                        <input type="text" class="form-control" id="inputActions1" placeholder="Actions" name="actions">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="inputBlocks1">Blocks</label>
                        <input type="text" class="form-control" id="inputBlocks1" placeholder="Blocks" name="blocks">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="inputTimeSince1">Time Since</label>
                        <input type="text" class="form-control" id="inputTimeSince1" placeholder="Time Since" name="timeSince">
                    </div>

                    <button type="submit" class="btn btn-default">Search</button>
                </form>

                <c:choose>
                    <c:when test="${not empty results}">
                        <h2>Results</h2>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th>World</th>
                                    <th>Location</th>
                                    <th>Action</th>
                                    <th>Player</th>
                                    <th>Data</th>
                                    <th>Timestamp</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${results}" var="element">
                                    <tr>
                                        <th>${world}</th>
                                        <td>${location}</td>
                                        <td>${action}</td>
                                        <td>${player}</td>
                                        <td>${data}</td>
                                        <td>${timeStamp}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </c:otherwise>
        </c:choose>
    </jsp:body>
</t:template>
