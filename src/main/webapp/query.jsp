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
            <c:when test="${connected == 'false'}">
                <p>Please connect to the Insight logging database first.</p>
                <p><a class="btn btn-lg btn-primary" href="connect" role="button">Connect &raquo;</a></p>
            </c:when>
            <c:otherwise>
                <h2>Query Info</h2>
                <form method="POST" form class="form-inline" action="query" role="form">
                    <div class="row">
                        <div class="form-group col-xs-4">
                            <input type="text" class="form-control" placeholder="World" name="world">
                        </div>
                        <div class="form-group col-xs-2">
                            <input type="text" class="form-control" placeholder="X" name="x">
                        </div>
                        <div class="form-group col-xs-2">
                            <input type="text" class="form-control" placeholder="Y" name="y">
                        </div>
                        <div class="form-group col-xs-2">
                            <input type="text" class="form-control" placeholder="Z" name="z">
                        </div>
                    </div>
                    </br>
                    <div class="row">
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Radius" name="radius">
                        </div>
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Time Since" name="timeSince">
                        </div>
                    </div>
                    </br>
                    <div class="row">
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Players" name="players">
                        </div>
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Entities" name="entities">
                        </div>
                    </div>
                    </br>
                    <div class="row">
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Actions" name="actions">
                        </div>
                        <div class="form-group col-xs-5">
                            <input type="text" class="form-control" placeholder="Blocks" name="blocks">
                        </div>
                    </div>
                    </br>
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
