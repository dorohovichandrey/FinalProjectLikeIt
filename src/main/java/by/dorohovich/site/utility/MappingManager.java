package by.dorohovich.site.utility;

import java.util.ResourceBundle;

/**
 * Created by User on 26.11.2016.
 */
public class MappingManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("mapping.config");

    private MappingManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
