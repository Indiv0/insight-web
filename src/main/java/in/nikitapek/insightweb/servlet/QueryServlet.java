package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class QueryServlet extends HttpServlet {
    private static final String page = "query.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gets the query string provided in the URL (e.g. "/users?register").
        String queryString = request.getQueryString();

        switch (queryString) {
            case "changePassword":
                changePasswordForm(request, response);
                break;
        }

        doGet(request, response);
    }

    private static void changePasswordForm(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getUserPrincipal().getName();
        String password = request.getParameter("password");

        if (username == null | password == null) {
            return;
        }

        boolean passwordChanged = Util.changePassword(username, password);

        String info = "";
        if (passwordChanged) {
            info += "<h3>Password Change Successful</h3>\n";
            info += "<p>Password for \"" + username + "\" was successfully changed.</p>";
        } else {
            info += "<h3>Password Change Failed</h3>\n";
            info += "<p>Password for \"" + username + "\" was not changed.</p>";
        }
        request.setAttribute("info", info);
    }
}
