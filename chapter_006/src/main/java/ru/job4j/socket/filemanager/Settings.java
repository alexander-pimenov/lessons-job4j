package ru.job4j.socket.filemanager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Settings
 *
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Settings {

    private static final Properties PROPERTIES = new Properties();
    /**
     * Constructor.
     * @param propertiesFileName - file of settings.
     */
    public Settings(String propertiesFileName) {
        ClassLoader classLoader = Settings.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(propertiesFileName)) {
            assert inputStream != null;
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return PROPERTIES.getProperty(key);
    }
}