package in.nikitapek.insightweb;

import in.nikitapek.marble.sql.MetadataQueries;
import in.nikitapek.marble.sql.SQLConnector;

import javax.servlet.http.HttpSession;

public class SQL {
    public static final String columnNames = "`id`, `x`, `y`, `z`";
    public static final String databaseName = "marbleTest";
    public static final String tableName = "data";

    public static void initializeSQL(String url, String username, String password, String port) {
        if (url == null || username == null || password == null || port == null) {
            throw new RuntimeException("Invalid configuration. Make sure to set dbhostname, dbusername, dbpassword, and dbport!");
        }

        SQLConnector.initializeSQL(url, username, password, port, databaseName, true);
    }

    public static void endTest() {
        MetadataQueries.dropDatabase(databaseName);
        SQLConnector.disconnect();
    }

    public static boolean isConnected() {
        return SQLConnector.getConnection() != null;
    }
}
