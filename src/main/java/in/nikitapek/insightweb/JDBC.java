package in.nikitapek.insightweb;

import org.apache.catalina.realm.JDBCRealm;

public class JDBC {
    public static JDBCRealm createJDBCRealm(String driverName, String connectionName, String connectionPassword, String connectionURL) {
        // Create a new JDBCRealm instance
        JDBCRealm realm = new JDBCRealm();
        realm.setDriverName(driverName);
        realm.setConnectionName(connectionName);
        realm.setConnectionPassword(connectionPassword);
        realm.setConnectionURL(connectionURL);

        return realm;
    }
}
