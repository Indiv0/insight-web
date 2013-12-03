<%@tag description="Template" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@attribute name="script" fragment="true" %>
<%@attribute name="css" fragment="true" %>

<!DOCTYPE html>
<html>
    <head>
    <t:header>
        <jsp:attribute name="title">
          Insight | Web Administration
        </jsp:attribute>
    </t:header>
    <jsp:invoke fragment="css"/>
    </head>
    <body>
        <!-- Static navbar -->
        <div class="navbar navbar-default navbar-static-top" role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="/">Insight</a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/">Home</a></li>
                        <li><a href="about">About</a></li>
                        <c:if test="${not empty pageContext.request.remoteUser}">
                            <li><a href="connect">Connect</a></li>
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="settings">Change Settings</a></li>
                                    <li class="divider"></li>
                                    <li class="dropdown-header">Administration</li>
                                    <li><a href="users">User Administration</a></li>
                                </ul>
                            </li>
                        </c:if>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="https://github.com/Indiv0/insight-web/">GitHub</a></li>
                        <li><a href="http://dev.bukkit.org/bukkit-plugins/insight/">BukkitDev</a></li>
                        <li><a href="http://reddit.com/r/MinerApocalypse">MinerAp</a></li>
                        <c:if test="${not empty pageContext.request.remoteUser}">
                            <li class="active"><a href="logout">Logout</a></li>
                        </c:if>
                    </ul>
                </div><!--/.nav-collapse -->
            </div>
        </div>

        <div class="container">
            <jsp:doBody/>
        </div> <!-- /container -->


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
        <script src="https://netdna.bootstrapcdn.com/bootstrap/3.0.2/js/bootstrap.min.js"></script>
        <jsp:invoke fragment="script"/>
    </body>
</html>
