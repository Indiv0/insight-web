package in.nikitapek.insightweb.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailedServlet extends HttpServlet {
    private static final String page = "login.jsp";

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = "<h1>Login Failed</h1>";
        request.setAttribute("status", status);

        request.getRequestDispatcher(page).forward(request, response);
    }
}