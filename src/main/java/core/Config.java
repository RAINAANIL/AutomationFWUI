package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Properties;

public class Config {

    static Properties properties;

    public static void setPropertyPath() {
        if (properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("src\\main\\resources\\configuration.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static String getValue(String property) {
        return properties.getProperty(property);
    }

    public static String getEncryptedValue(String property) {
        return properties.getProperty(Base64.getDecoder().decode(property).toString());
    }
}
