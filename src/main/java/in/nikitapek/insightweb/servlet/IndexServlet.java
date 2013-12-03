package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class IndexServlet extends HttpServlet {
    private static final String page = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("connected", Util.insightConnection.isConnected() ? "yes" : "no");

        String connectionInfo;
        if (Util.insightConnection.isConnected()) {
            connectionInfo = "<div class=\"alert alert-info\">You're currently connected to the Insight logging server.</div>";
            connectionInfo += "<p> Username: " + Util.insightConnection.getUsername() + "</p>";
            connectionInfo += "<p> URL: " + Util.insightConnection.getURL() + "</p>";
        } else {
            connectionInfo = "<div class=\"alert alert-warning\">You're not currently connected to the Insight logging server.</div>";
            connectionInfo += "<p><a class=\"btn btn-lg btn-primary\" href=\"connect\" role=\"button\">Connect SQL &raquo;</a></p>";
        }

        request.setAttribute("connectionInfo", connectionInfo);
        request.getRequestDispatcher(page).forward(request, response);
    }
}