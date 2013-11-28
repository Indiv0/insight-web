package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConfigureServlet extends HttpServlet {
    private static final String page = "configure.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("someConfigKey", Util.configuration.getProperty("someConfigKey"));

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String someConfigKey = request.getParameter("someConfigKey");

        if (someConfigKey == null) {
            request.setAttribute("problem", "Values cannot be null.");

            request.getRequestDispatcher(page).forward(request, response);
        }
        else {
            Util.configuration.setProperty("someConfigKey", someConfigKey);

            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}