package in.nikitapek.insightweb.util;

import in.nikitapek.insightjdbc.RealmProperties;
import in.nikitapek.insightweb.Configuration;
import in.nikitapek.insightweb.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Util {
    private static final String ADD_USER_QUERY = "INSERT INTO `tomcat_users` (`user_name`, `password`) VALUES (?, ?);";
    private static final String ADD_USER_ROLE_QUERY = "INSERT INTO `tomcat_users_roles` (`user_name`, `role_name`) VALUES (?, ?);";

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
}
