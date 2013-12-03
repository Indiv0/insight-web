package in.nikitapek.insightweb;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import in.nikitapek.insightjdbc.RealmProperties;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLConnection {
    private final HikariConfig config = new HikariConfig();
    private DataSource pool;

    protected SQLConnection() {}

    public SQLConnection(RealmProperties properties) {
        this(properties.getUsername(), properties.getPassword(), properties.getURL(), properties.getPort(), properties.getDatabaseName());
    }
    public SQLConnection(String username, String password, String url, String port, String databaseName) {
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("org.mariadb.jdbc.MySQLDataSource");
        config.addDataSourceProperty("url", "jdbc:mysql://" + url + ":" + port + "/" + databaseName);
        config.addDataSourceProperty("user", username);
        config.addDataSourceProperty("password", password);
        pool = new HikariDataSource(config);
    }

    public String getUsername() {
        return config.getDataSourceProperties().getProperty("user");
    }

    public String getURL() {
        return config.getDataSourceProperties().getProperty("url");
    }

    public boolean isConnected() {
        return getConnection() != null;
    }

    public Connection getConnection() {
        Connection connection = null;

        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            System.out.println("[insight-web] Failed to retrieve database connection.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return connection;
    }

    public void disconnect(Connection connection) {
        if (connection == null) {
            return;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("[insight-web] Error while closing connection.");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
