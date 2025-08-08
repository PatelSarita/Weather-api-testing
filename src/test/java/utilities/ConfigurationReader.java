package utilities;

import java.io.FileOutputStream;
import java.util.Properties;

import java.io.*;

//public class ConfigurationReader {
//    private static Properties properties;
//
//    static {
//        try {
//            properties = new Properties();
//            InputStream input = ConfigurationReader.class.getClassLoader()
//                    .getResourceAsStream("configuration.properties");
//
//            if (input == null) {
//                throw new FileNotFoundException("configuration.properties not found in classpath");
//            }
//
//            properties.load(input);
//            input.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static String get(String keyName) {
//        return properties.getProperty(keyName);
//    }
//
//    // Optional: You can keep this, but it won't work with classpath-based file loading.
//    public static void set(String keyName, String value) {
//        try {
//            // This only works if the file is in the file system, not inside a JAR or classpath
//            File file = new File("configuration.properties");
//            FileOutputStream output = new FileOutputStream(file);
//
//            properties.setProperty(keyName, value);
//            properties.store(output, null);
//            output.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

public class ConfigurationReader {
    private static Properties properties;

    static {

        try {
            String path = "configuration.properties";
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
        String path = "configuration.properties";

        try {
            OutputStream output = new FileOutputStream(path);
            properties.setProperty(keyName, value);
            properties.store(output,null);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
