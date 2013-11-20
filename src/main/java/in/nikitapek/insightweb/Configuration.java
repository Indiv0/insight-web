package in.nikitapek.insightweb;

import org.javatuples.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class Configuration {
    private static Set<Pair<String, String>> defaultProperties = new HashSet<>();
    private static String fileLocation = getFileLocation();

    static {
        defaultProperties.add(new Pair("dbName", "insight"));
        defaultProperties.add(new Pair("dbUsername", "insightuser"));
        defaultProperties.add(new Pair("dbPassword", "insight"));
        defaultProperties.add(new Pair("dbURL", "localhost"));
        defaultProperties.add(new Pair("dbPort", "3306"));
    }

    private static Properties properties = null;

    private Configuration() {}

    public static void initialize() {
        System.out.println("1");
        if (properties != null) {
            return;
        }

        System.out.println("Initializing properties");
        try {
            System.out.println("Loading properties");
            properties = loadProperties();
            return;
        } catch (IOException e) {
            System.out.println("Error loading properties");
            e.printStackTrace();
        }

        System.out.println("Saving default properties");
        properties = saveDefaultProperties();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static void setDatabaseProperties(String databaseName, String username, String password, String url, String port) {
        properties.setProperty("dbName", databaseName);
        properties.setProperty("dbUsername", username);
        properties.setProperty("dbPassword", password);
        properties.setProperty("dbUrl", url);
        properties.setProperty("dbPort", port);
        saveProperties(properties);
    }

    private static Properties loadProperties() throws IOException {
        FileInputStream propFile = new FileInputStream(fileLocation);

        Properties p = new Properties();
        p.load(propFile);

        return p;
    }

    private static Properties saveDefaultProperties() {
        Properties properties = new Properties();

        for (Pair<String, String> pair : defaultProperties) {
            properties.setProperty(pair.getValue0(), pair.getValue1());
        }

        saveProperties(properties);

        return properties;
    }

    private static void saveProperties(Properties properties) {
        try {
            properties.store(new FileOutputStream(fileLocation), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getFileLocation() {
        String confDir = System.getProperty("insightweb.confdir", null);
        String fileLocation = "insightweb.properties";

        if (confDir != null) {
            File file1 = new File(confDir);
            File file2 = new File(file1, fileLocation);
            fileLocation = file2.getPath();
        }

        return fileLocation;
    }
}
