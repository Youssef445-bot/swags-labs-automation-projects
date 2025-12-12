package Utilites;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class datautil {

    private static final String Test_data_path = "src/test/resources/testdata/";

    //Todo: reading data from json file
    public static String gsonfile(String filename, String field) throws FileNotFoundException {   // filename + field name
        try {
            FileReader fileReader = new FileReader(Test_data_path + filename + ".json");
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            logsutils.error(e.getMessage());
            return "0";
        }

    }

    // Todo: reading data from properties file
    public static String propertiesfile(String filename, String value) throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(Test_data_path + filename + ".properties"));
        return properties.getProperty(value);

    }
}
