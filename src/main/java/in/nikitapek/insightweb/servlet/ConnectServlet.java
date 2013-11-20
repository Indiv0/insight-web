package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.SQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ConnectServlet extends HttpServlet {
    private static final String page = "connect.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch = null;
        HttpSession curr = request.getSession();
        String url = request.getParameter("url");
        String port = request.getParameter("port");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (url.equals("URL") || port.equals("Port") || username.equals("Username") || password.equals("Password")){
            request.setAttribute("problem", "Please fill all fields");

            dispatch = request.getRequestDispatcher(page);
            dispatch.forward(request, response);
        } else if (url == null || port == null || username == null || password == null) {
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

            SQL.initializeSQL(url, username, password, port);

            if(SQL.isConnected()){
                curr.setAttribute("url", url);
                curr.setAttribute("username", username);
                curr.setAttribute("password", password);
                curr.setAttribute("port", port);
                curr.setAttribute("valid", "true");
                dispatch = request.getRequestDispatcher("index.jsp");
                dispatch.forward(request, response);
            } else{
                request.setAttribute("problem", "Failed to connect to your server.");
                dispatch = request.getRequestDispatcher(page);
                dispatch.forward(request, response);
            }
        }
    }
}