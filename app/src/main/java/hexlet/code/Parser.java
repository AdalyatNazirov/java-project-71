package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Provides utility methods for parsing JSON and YAML data into Java maps.
 */
public class Parser {
    public static final ObjectMapper MAPPER_JSON = new ObjectMapper(); // create once, reuse
    public static final ObjectMapper YAML_JSON = new ObjectMapper(new YAMLFactory()); // create once, reuse

    /**
     * Parses a YAML string into a Java map.
     *
     * @param content the YAML string to parse
     * @return a Java map representing the parsed YAML data
     * @throws JsonProcessingException if an error occurs during parsing
     */
    public static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        return YAML_JSON.readValue(content, new TypeReference<>() {
        });
    }

    /**
     * Parses a Json string into a Java map.
     *
     * @param content the Json string to parse
     * @return a Java map representing the parsed YAML data
     * @throws JsonProcessingException if an error occurs during parsing
     */
    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        return MAPPER_JSON.readValue(content, new TypeReference<>() {
        });
    }

    /**
     * Parses a string into a Java map based on the specified file format.
     *
     * @param content    the string to parse
     * @param fileFormat the file format of the string (e.g. "json", "yaml", "yml")
     * @return a Java map representing the parsed data
     * @throws JsonProcessingException   if an error occurs during parsing
     * @throws InvalidParameterException if the file format is not supported
     */
    public static Map<String, Object> parse(String content, String fileFormat) throws JsonProcessingException {
        return switch (fileFormat.toLowerCase()) {
            case "json" -> parseJson(content);
            case "yaml", "yml" -> parseYaml(content);
            default -> throw new InvalidParameterException("file format not supported: " + fileFormat);
        };
    }
}
