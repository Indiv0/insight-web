package in.nikitapek.insightweb.util;

import in.nikitapek.insightjdbc.RealmProperties;
import in.nikitapek.insightjdbc.SQL;
import in.nikitapek.insightweb.Configuration;
import in.nikitapek.insightweb.SQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Util {
    private static final String ADD_USER_QUERY = "INSERT INTO `tomcat_users` (`user_name`, `password`) VALUES (?, ?);";
    private static final String ADD_ROLE_QUERY = "INSERT INTO `tomcat_roles` (`role_name`) VALUES (?);";
    private static final String REMOVE_USER_QUERY = "DELETE FROM `tomcat_users` WHERE `user_name` = ?;";
    private static final String REMOVE_USER_ROLES_QUERY = "DELETE FROM `tomcat_users_roles` WHERE `user_name` = ?;";
    private static final String REMOVE_ROLE_QUERY = "DELETE FROM `tomcat_roles` WHERE `role_name` = ?;";
    private static final String ADD_USER_ROLE_QUERY = "INSERT INTO `tomcat_users_roles` (`user_name`, `role_name`) VALUES (?, ?);";
    private static final String GET_USERS_QUERY = "SELECT * FROM `tomcat_users_roles`";
    private static final String GET_ROLES_QUERY = "SELECT * FROM `tomcat_roles`";
    private static final String CHANGE_PASSWORD_QUERY = "UPDATE `tomcat_users` SET `password`=? WHERE `user_name`=?;";

    private Util() {}

    public static Configuration configuration = new Configuration("insightweb.properties");
    public static SQLConnection authConnection = new SQLConnection(new RealmProperties("insightweb-auth.properties"));
    public static SQLConnection insightConnection = new SQLConnection(new RealmProperties("insightweb-main.properties"));

    public static void initialize() {
    }

    public static boolean addUser(String username, String password, String rolename)
    {
        // If a rolename hasn't been provided, we default to 'insight-user'.
        if ("".equals(rolename)) {
            rolename = "insight-user";
        }

        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ADD_USER_ROLE_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, rolename);
            status = preparedStatement.executeUpdate();
            SQL.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to register user with credentials: '" + username + "', '" + password + "', '" + rolename + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the user was successfully registered.
        return status >= 0;
    }

    public static boolean addRole(String rolename)
    {
        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ROLE_QUERY);
            preparedStatement.setString(1, rolename);
            preparedStatement.executeUpdate();
            SQL.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to add role: '" + rolename + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the role was successfully added.
        return status >= 0;
    }

    public static boolean removeUser(String username)
    {
        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_USER_ROLES_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(REMOVE_USER_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            SQL.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to remove user '" + username + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the user was successfully removed.
        return status >= 0;
    }

    public static boolean removeRole(String rolename)
    {
        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_ROLE_QUERY);
            preparedStatement.setString(1, rolename);
            preparedStatement.executeUpdate();
            SQL.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to remove role '" + rolename + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the role was successfully removed.
        return status >= 0;
    }

    public static boolean changePassword(String username, String password)
    {
        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_QUERY);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            SQL.commit(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to change password for '" + username + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the password was successfully changed.
        return status >= 0;
    }

    public static Map<String, String> getUsers()
    {
        HashMap<String, String> users = new HashMap<>();
        Connection connection = authConnection.getConnection();

        try {
            Statement getUsersStatement = connection.createStatement();
            ResultSet resultSet = getUsersStatement.executeQuery(GET_USERS_QUERY);
            SQL.commit(connection);

            while (resultSet.next()) {
                users.put(resultSet.getString("user_name"), resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to retrieve users and roles.");
            return null;
        } finally {
            authConnection.disconnect(connection);
        }

        return users;
    }

    public static List<String> getRoles()
    {
        List<String> roles = new ArrayList<>();
        Connection connection = authConnection.getConnection();

        try {
            Statement getRolesStatement = connection.createStatement();
            ResultSet resultSet = getRolesStatement.executeQuery(GET_ROLES_QUERY);
            SQL.commit(connection);

            while (resultSet.next()) {
                roles.add(resultSet.getString("role_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to retrieve roles.");
            return null;
        } finally {
            authConnection.disconnect(connection);
        }

        return roles;
    }
}
