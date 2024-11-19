package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

/**
 * Provides utility methods for parsing YAML data into Java maps.
 */
public class YamlParser {
    public static final ObjectMapper YAML_JSON = new ObjectMapper(new YAMLFactory()); // create once, reuse

    /**
     * Parses a string into a Java map based on the specified file format.
     *
     * @param content the string to parse
     * @return a Java map representing the parsed data
     * @throws JsonProcessingException if an error occurs during parsing
     */
    public static Map<String, Object> parse(String content) throws JsonProcessingException {
        return YAML_JSON.readValue(content, new TypeReference<>() {
        });
    }
}
