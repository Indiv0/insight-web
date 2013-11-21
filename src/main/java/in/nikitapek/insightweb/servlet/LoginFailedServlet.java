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

        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        String exceptionType = (String) request.getAttribute("javax.servlet.error.exception_type");
        String errorMsg = (String) request.getAttribute("javax.servlet.error.message");

        System.out.println(statusCode);
        System.out.println(exceptionType);
        System.out.println(errorMsg);
        System.out.println(request.getUserPrincipal());
        System.out.println(request.getRemoteUser());

        request.getRequestDispatcher(page).forward(request, response);
    }
}