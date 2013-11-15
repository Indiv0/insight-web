package in.nikitapek.insightweb;

import in.nikitapek.marble.sql.SQLConnector;

public class Main {
    public static final String columnNames = "`id`, `x`, `y`, `z`";
    public static final String databaseName = "marbleTest";
    public static final String tableName = "data";

    public static void initializeSQL() {
        String dbhostname = System.getProperty("dbhostname");
        String dbusername = System.getProperty("dbusername");
        String dbpassword = System.getProperty("dbpassword");
        String dbport = System.getProperty("dbport");

        if (dbhostname == null || dbusername == null || dbpassword == null || dbport == null) {
            throw new RuntimeException("Invalid configuration. Make sure to set dbhostname, dbusername, dbpassword, and dbport!");
        }

        SQLConnector.initializeSQL(dbhostname, dbusername, dbpassword, dbport, databaseName, true);

        // Drop the database in case it remains from a previous test case.
        dropDatabase(databaseName);
    }

    public static void endTest() {
        dropDatabase(databaseName);
        SQLConnector.disconnect();
    }
}
