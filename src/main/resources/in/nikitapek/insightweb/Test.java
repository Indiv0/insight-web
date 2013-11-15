package in.nikitapek.insightweb;

import in.nikitapek.marble.sql.Handler;
import in.nikitapek.marble.sql.SQLConnector;
import in.nikitapek.marble.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private void insertRandomHandler() {
        Connection connection = SQLConnector.getConnection();

        Handler handler = Util.generateRandomHandler();

        Statement statement = null;
        try {
            if (!SQLConnector.autoCommitStatements) {
                connection.setAutoCommit(false);
            }

            statement = connection.createStatement();
            connection.createStatement().executeUpdate("INSERT INTO `" + SQL.databaseName + "`.`" + SQL.tableName + "` (x, y, z) VALUES ('" + (int) handler.getX() + "', '" + (int) handler.getY() + "', '" + (int) handler.getZ() + "')");

            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
        } finally {
            Util.closeStatement(statement);
        }
    }
}
