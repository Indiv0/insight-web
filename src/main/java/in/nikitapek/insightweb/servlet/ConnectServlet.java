package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.SQLConnection;
import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConnectServlet extends HttpServlet {
    private static final String page = "connect.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String url = request.getParameter("url");
        String port = request.getParameter("port");

        try {
            int portNumber = Integer.parseInt(port);

            if (portNumber < 0 || portNumber > 65535) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            request.setAttribute("problem", "Please enter a valid number for the port");

            request.getRequestDispatcher(page).forward(request, response);
            return;
        }

        Util.insightConnection = new SQLConnection(username, password, url, port, "insight");

        if(Util.insightConnection.isConnected()){
            response.sendRedirect("index");
        } else{
            request.setAttribute("problem", "Failed to connect to your server.");
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
