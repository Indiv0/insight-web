package in.nikitapek.insightweb;

import in.nikitapek.marble.sql.Handler;
import in.nikitapek.marble.sql.MarbleDatabase;
import in.nikitapek.marble.sql.SQLConnector;
import in.nikitapek.marble.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static in.nikitapek.marble.sql.MetadataQueries.dropDatabase;
import static in.nikitapek.marble.util.Util.closeStatement;
import static in.nikitapek.marble.util.Util.generateRandomHandler;

public class Test {
    private static final int insertionSize = 100;

    static String insertionTest(boolean prepareStatements, boolean reusePreparedStatements, boolean batchStatements, boolean autoCommitStatements) {
        SQLConnector.prepareStatements = prepareStatements;
        SQLConnector.reusePreparedStatements = reusePreparedStatements;
        SQLConnector.batchStatements = batchStatements;
        SQLConnector.autoCommitStatements = autoCommitStatements;
        Connection connection = SQLConnector.getConnection();

        dropDatabase(SQL.databaseName);
        MarbleDatabase.setupDatabase(SQL.tableName);

        List<Handler> handlerList = new ArrayList<>();
        for (int i = 0; i < insertionSize; i++) {
            handlerList.add(generateRandomHandler());
        }

        Long initialTime = System.currentTimeMillis();

        Statement statement = null;
        PreparedStatement preparedStatement = null;

        try {
            connection.setAutoCommit(SQLConnector.autoCommitStatements);

            if (SQLConnector.prepareStatements) {
                preparedStatement = connection.prepareStatement("INSERT INTO `" + SQL.databaseName + "`.`" + SQL.tableName + "` (`x`, `y`, `z`) VALUES (?, ?, ?)");

                for (Handler handler : handlerList) {
                    if (!reusePreparedStatements) {
                        preparedStatement = connection.prepareStatement("INSERT INTO `" + SQL.databaseName + "`.`" + SQL.tableName + "` (`x`, `y`, `z`) VALUES (?, ?, ?)");
                    }
                    preparedStatement.setInt(1, (int) handler.getX());
                    preparedStatement.setInt(2, (int) handler.getY());
                    preparedStatement.setInt(3, (int) handler.getZ());

                    if (SQLConnector.batchStatements) {
                        preparedStatement.addBatch();
                    } else {
                        preparedStatement.executeUpdate();
                    }
                }

                if (SQLConnector.batchStatements) {
                    preparedStatement.executeBatch();
                }
            } else {
                String query;
                statement = connection.createStatement();

                for (Handler handler : handlerList) {
                    query = "INSERT INTO `" + SQL.databaseName + "`.`" + SQL.tableName + "` (`x`, `y`, `z`) VALUES ('" + (int) handler.getX() + "', '" + (int) handler.getY() + "', '" + (int) handler.getZ() + "')";

                    if (SQLConnector.batchStatements) {
                        statement.addBatch(query);
                    } else {
                        statement.executeUpdate(query);
                    }
                }

                if (SQLConnector.batchStatements) {
                    statement.executeBatch();
                }
            }

            if (!SQLConnector.autoCommitStatements) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            System.out.println("Failed to test handler insertion.");
        } finally {
            closeStatement(statement);
            closeStatement(preparedStatement);
        }

        Long deltaTime = System.currentTimeMillis() - initialTime;

        SQL.endTest();

        return getOperationDescriptionString(insertionSize, deltaTime);
    }

    private static String getOperationDescriptionString(int insertionSize, long deltaTime) {
        String descriptionString;

        if (!SQLConnector.prepareStatements) {
            descriptionString = "Regular\t\t\t\t\t";
        } else {
            descriptionString = "Prepared\t";
            if (SQLConnector.reusePreparedStatements) {
                descriptionString += " Reused\t\t";
            } else {
                descriptionString += "\t\t\t";
            }
        }

        if (SQLConnector.batchStatements) {
            descriptionString += "Batched\t\t\t";
        } else {
            descriptionString += "Unbatched\t\t";
        }

        if (SQLConnector.autoCommitStatements) {
            descriptionString += "AutoCommit\t\t\t";
        } else {
            descriptionString += "NonAutoCommit\t\t";
        }

        descriptionString += getOperationSpeed(insertionSize, deltaTime) + "\t";
        descriptionString += insertionSize + " records in " + deltaTime + " ms";

        return descriptionString;
    }

    private static String getOperationSpeed(int insertionSize, long deltaTime) {
        return ((int) ((double) insertionSize / deltaTime * 1000)) + " ops/sec";
    }
}
