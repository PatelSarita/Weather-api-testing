package utilities;

import java.io.FileOutputStream;
import java.util.Properties;
import java.io.*;

public class ConfigurationReader {
    private static Properties properties;

    static {

        try {
            String path = "src/test/resources/configuration.properties";
            FileInputStream input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String get(String keyName){
        return properties.getProperty(keyName);
    }

    public static void set(String keyName, String value){
        String path = "src/test/resources/configuration.properties";

        try {
            OutputStream output = new FileOutputStream(path);
            properties.setProperty(keyName, value);
            properties.store(output,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
