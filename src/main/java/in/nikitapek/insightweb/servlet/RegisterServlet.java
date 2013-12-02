package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private static final String page = "register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> users = Util.getUsers();
        request.setAttribute("users", users);

        String status = (String) request.getAttribute("status");

        if ("added".equals(status)) {
            String registrationInfo = "";
            registrationInfo += "<h1>Registration Successful!</h1>";
            registrationInfo += "<p>\"" + request.getAttribute("username") + "\" was successfully registered.</p>";
            request.setAttribute("registrationInfo", registrationInfo);
        } else if ("failed".equals(status)) {
            String registrationInfo = "";
            registrationInfo += "<h1>Registration Failed.</h1>";
            registrationInfo += "<p>\"" + request.getAttribute("username") + "\" was not registered.</p>";
            request.setAttribute("registrationInfo", registrationInfo);
        }

        request.setAttribute("status", null);

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatch = null;
        HttpSession curr = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username.equals("Username") || password.equals("Password")){
            request.setAttribute("problem", "Please fill all fields");
        } else if (username == null || password == null) {
            request.setAttribute("problem", "Values cannot be null.");
        } else {
            boolean userAdded = Util.addUser(username, password);

            if (userAdded) {
                request.setAttribute("username", username);
                request.setAttribute("status", "added");
            } else {
                request.setAttribute("status", "failed");
            }
        }

        request.getRequestDispatcher(page).forward(request, response);
    }
}
