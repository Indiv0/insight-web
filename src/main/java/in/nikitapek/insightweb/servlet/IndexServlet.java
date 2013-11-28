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

        if (Util.insightConnection.isConnected()) {
            String connectionInfo = "";
            connectionInfo += "<p> Username: " + request.getSession().getAttribute("username") + "</p>";
            connectionInfo += "<p> URL: " + request.getSession().getAttribute("url") + "</p>";
            connectionInfo += "<p> Port: " + request.getSession().getAttribute("port") + "</p>";
            request.setAttribute("connectionInfo", connectionInfo);
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}