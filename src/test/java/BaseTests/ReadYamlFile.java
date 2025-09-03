package BaseTests;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class ReadYamlFile {

    public static Map<String, Object> read_config() throws FileNotFoundException {
        Yaml yml = new Yaml();
        String path = Constants.CONFIGURATION_YAML_PATH;
        InputStream inputStream = new FileInputStream(path);
        Map<String, Object> yamlFile =  yml.load(inputStream);
        Map<String, Object> config =  (Map<String, Object>) yamlFile.get("configurations");
        return config;
    }



}
