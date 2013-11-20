package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.Configuration;
import in.nikitapek.insightweb.JDBC;
import org.apache.catalina.realm.JDBCRealm;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConfigureServlet extends HttpServlet {
    private static final String page = "configure.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher(page).forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch = null;
        HttpSession curr = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = request.getParameter("url");
        String port = request.getParameter("port");

        if (url == null || port == null || username == null || password == null) {
            request.setAttribute("problem", "Values cannot be null.");

            dispatch = request.getRequestDispatcher(page);
            dispatch.forward(request, response);
        }
        else {
            try {
                int portNumber = Integer.parseInt(port);

                if (portNumber < 0 || portNumber > 65535) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException ex) {
                request.setAttribute("problem", "Please enter a valid number for the port");

                dispatch = request.getRequestDispatcher(page);
                dispatch.forward(request, response);
            }

            // Test the connection with the new options.
            JDBCRealm testRealm = new JDBCRealm();
            JDBC.configureRealm(new JDBCRealm(), username, password, url, port);
            if (testRealm.getUserTable() == null) {
                request.setAttribute("problem", "Failed to connect to your server.");
                dispatch = request.getRequestDispatcher(page);
                dispatch.forward(request, response);
                return;
            }

            JDBC.configureRealm(JDBC.realm, username, password, url, port);
            Configuration.setDatabaseProperties("insight", username, password, url, port);

            dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }
    }
}