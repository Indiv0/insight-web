package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QueryServlet extends HttpServlet {
    private static final String page = "query.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("connected", Util.insightConnection.isConnected() ? "true" : "false");
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gets the query string provided in the URL (e.g. "/users?register").
        String queryString = request.getQueryString();

        switch (queryString) {
            case "":
                queryForm(request, response);
                break;
        }

        doGet(request, response);
    }

    private static void queryForm(HttpServletRequest request, HttpServletResponse response) {
        String worldAttribute = request.getParameter("world");
        String xAttribute = request.getParameter("x");
        String yAttribute = request.getParameter("y");
        String zAttribute = request.getParameter("z");
        String radiusAttribute = request.getParameter("radius");
        String playersAttribute = request.getParameter("players");
        String entitiesAttribute = request.getParameter("entities");
        String actionsAttribute = request.getParameter("actions");
        String blocksAttribute = request.getParameter("blocks");
        String timeSinceAttribute = request.getParameter("timeSince");

        String world;
        if (worldAttribute != null) {
            world = worldAttribute;
        }

        String x, y, z;
        if (xAttribute != null) {
            x = xAttribute;
        }
        if (yAttribute != null) {
            y = yAttribute;
        }
        if (zAttribute != null) {
            z = zAttribute;
        }

        String radius;
        if (radiusAttribute != null) {
            radius = radiusAttribute;
        }

        List<String> players;
        if (playersAttribute != null) {
            // Splits comma-delimited player list into the player names.
            players = Arrays.asList(playersAttribute.split("\\s*,\\s*"));
        }

        List<String> entities;
        if (entitiesAttribute != null) {
            entities = Arrays.asList(entitiesAttribute.split("\\s*,\\s*"));
        }

        List<String> actions;
        if (actionsAttribute != null) {
            actions = Arrays.asList(actionsAttribute.split("\\s*,\\s*"));
        }

        List<String> blocks;
        if (blocksAttribute != null) {
            blocks = Arrays.asList(blocksAttribute.split("\\s*,\\s*"));
        }

        String timeSince;
        if (timeSinceAttribute != null) {
            timeSince = timeSinceAttribute;
        }
    }
}
