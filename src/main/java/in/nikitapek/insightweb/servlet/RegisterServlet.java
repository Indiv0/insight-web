package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.JDBC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    private static final String page = "register.jsp";

    @Override
    public void init() throws ServletException {
        super.init();

        JDBC.initialize(getServletContext());
    }

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

        if (username.equals("Username") || password.equals("Password")){
            request.setAttribute("problem", "Please fill all fields");

            dispatch = request.getRequestDispatcher(page);
            dispatch.forward(request, response);
        } else if (username == null || password == null) {
            request.setAttribute("problem", "Values cannot be null.");

            dispatch = request.getRequestDispatcher(page);
            dispatch.forward(request, response);
        } else if (JDBC.userExists(username)) {
            request.setAttribute("problem", "Username already taken.");

            dispatch = request.getRequestDispatcher(page);
            dispatch.forward(request, response);
        }
        else {
            curr.setAttribute("username", username);
            curr.setAttribute("password", password);
            curr.setAttribute("valid", "true");
            dispatch = request.getRequestDispatcher("index.jsp");
            dispatch.forward(request, response);
        }
    }
}