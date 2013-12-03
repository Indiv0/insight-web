package in.nikitapek.insightweb.servlet;

import in.nikitapek.insightweb.util.Util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    private static final String page = "users.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> users = Util.getUsers();
        request.setAttribute("users", users);
        List<String> roles = Util.getRoles();
        request.setAttribute("roles", roles);

        request.getRequestDispatcher(page).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Gets the query string provided in the URL (e.g. "/users?register").
        String queryString = request.getQueryString();

        switch (queryString) {
            case "registerUser":
                registerUserForm(request, response);
                break;
            case "deleteUser":
                deleteUserForm(request, response);
                break;
            case "registerRole":
                registerRoleForm(request, response);
                break;
            case "deleteRole":
                deleteRoleForm(request, response);
                break;
        }

        doGet(request, response);
    }

    private static void registerUserForm(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rolename = request.getParameter("rolename");

        if (username == null || password == null) {
            return;
        }

        boolean userAdded = Util.addUser(username, password, rolename);

        String registrationInfo = "";
        if (userAdded) {
            registrationInfo += "<h3>Registration Successful</h3>\n";
            registrationInfo += "<p>\"" + username + "\" was successfully registered.</p>";
        } else {
            registrationInfo += "<h3>Registration Failed</h3>\n";
            registrationInfo += "<p>\"" + username + "\" was not registered.</p>";
        }
        request.setAttribute("registrationInfo", registrationInfo);
    }

    private static void deleteUserForm(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");

        if (username == null) {
            return;
        }

        boolean userDeleted = Util.removeUser(username);

        String registrationInfo = "";
        if (userDeleted) {
            registrationInfo += "<h3>User Removal Successful</h3>\n";
            registrationInfo += "<p>\"" + username + "\" was successfully removed.</p>";
        } else {
            registrationInfo += "<h3>User Removal Failed</h3>\n";
            registrationInfo += "<p>\"" + username + "\" was not removed.</p>";
        }
        request.setAttribute("registrationInfo", registrationInfo);
    }

    private static void registerRoleForm(HttpServletRequest request, HttpServletResponse response) {
        String rolename = request.getParameter("rolename");

        if (rolename == null) {
            return;
        }

        boolean roleAdded = Util.addRole(rolename);

        String registrationInfo = "";
        if (roleAdded) {
            registrationInfo += "<h3>Role Addition Successful</h3>\n";
            registrationInfo += "<p>\"" + rolename + "\" was successfully added.</p>";
        } else {
            registrationInfo += "<h3>Role Removal Failed</h3>\n";
            registrationInfo += "<p>\"" + rolename + "\" was not added.</p>";
        }
        request.setAttribute("registrationInfo", registrationInfo);
    }

    private static void deleteRoleForm(HttpServletRequest request, HttpServletResponse response) {
        String rolename = request.getParameter("rolename");

        if (rolename == null) {
            return;
        }

        boolean roleDeleted = Util.removeRole(rolename);

        String registrationInfo = "";
        if (roleDeleted) {
            registrationInfo += "<h3>Role Removal Successful</h3>\n";
            registrationInfo += "<p>\"" + rolename + "\" was successfully removed.</p>";
        } else {
            registrationInfo += "<h3>role Removal Failed</h3>\n";
            registrationInfo += "<p>\"" + rolename + "\" was not removed.</p>";
        }
        request.setAttribute("registrationInfo", registrationInfo);
    }
}
