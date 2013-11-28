package in.nikitapek.insightweb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {
    private static Map<String, String> defaultProperties = new HashMap<>();

    static {
        defaultProperties.put("someConfigKey", "aValue");
    }

    private String fileLocation;
    private Properties properties = null;

    public Configuration(String configurationFileLocation) {
        fileLocation = getFileLocation(configurationFileLocation);

        try {
            System.out.println("[insight-web] Loading properties.");
            properties = loadProperties();
            return;
        } catch (IOException e) {
            System.out.println("[insight-web] Error loading properties");
        }

        System.out.println("[insight-web] Saving default properties");
        properties = saveDefaultProperties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties(properties);
    }

    private Properties loadProperties() throws IOException {
        FileInputStream propFile = new FileInputStream(fileLocation);

        Properties p = new Properties();
        p.load(propFile);

        return p;
    }

    private Properties saveDefaultProperties() {
        Properties properties = new Properties();

        for (Map.Entry<String, String> entry : defaultProperties.entrySet()) {
            properties.setProperty(entry.getKey(), entry.getValue());
        }

        saveProperties(properties);

        return properties;
    }

    private void saveProperties(Properties properties) {
        try {
            properties.store(new FileOutputStream(fileLocation), null);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String getFileLocation(String fileLocation) {
        String confDir = System.getProperty("insightweb.confdir", null);

        if (confDir != null) {
            File file1 = new File(confDir);
            File file2 = new File(file1, fileLocation);
            fileLocation = file2.getPath();
        }

        return fileLocation;
    }
}
