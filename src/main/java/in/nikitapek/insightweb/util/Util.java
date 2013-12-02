package in.nikitapek.insightweb.util;

import in.nikitapek.insightjdbc.RealmProperties;
import in.nikitapek.insightweb.Configuration;
import in.nikitapek.insightweb.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;

public class Util {
    private static final String ADD_USER_QUERY = "INSERT INTO `tomcat_users` (`user_name`, `password`) VALUES (?, ?);";
    private static final String ADD_USER_ROLE_QUERY = "INSERT INTO `tomcat_users_roles` (`user_name`, `role_name`) VALUES (?, ?);";
    private static final String GET_USERS_QUERY = "SELECT * FROM `tomcat_users_roles`";

    private Util() {}

    public static Configuration configuration = new Configuration("insightweb.properties");
    public static SQLConnection authConnection = new SQLConnection(new RealmProperties("insightweb-auth.properties"));
    public static SQLConnection insightConnection = new SQLConnection(new RealmProperties("insightweb-main.properties"));

    public static void initialize() {
    }

    public static boolean addUser(String username, String password)
    {
        int status = 0;
        Connection connection = authConnection.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(ADD_USER_ROLE_QUERY);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, "insight-user");
            status = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[insight-web] Failed to register user with credentials: '" + username + "', '" + password + "'");
            return false;
        } finally {
            authConnection.disconnect(connection);
        }

        // If the number of affected rows was greater than zero, then the user was successfully registered.
        return status >= 0;
    }

    public static Map<String, String> getUsers()
    {
        HashMap<String, String> users = new HashMap<>();
        Connection connection = authConnection.getConnection();

        try {
            Statement getUsersStatement = connection.createStatement();
            ResultSet resultSet = getUsersStatement.executeQuery(GET_USERS_QUERY);
            
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
}
