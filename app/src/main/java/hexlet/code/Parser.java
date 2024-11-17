package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.security.InvalidParameterException;
import java.util.Map;

public class Parser {
    public static final ObjectMapper MAPPER_JSON = new ObjectMapper(); // create once, reuse
    public static final ObjectMapper YAML_JSON = new ObjectMapper(new YAMLFactory()); // create once, reuse

    public static Map<String, Object> parseYaml(String content) throws JsonProcessingException {
        return YAML_JSON.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parseJson(String content) throws JsonProcessingException {
        return MAPPER_JSON.readValue(content, new TypeReference<>() {
        });
    }

    public static Map<String, Object> parse(String content, String fileFormat) throws JsonProcessingException {
        return switch (fileFormat.toLowerCase()) {
            case "json" -> parseJson(content);
            case "yaml", "yml" -> parseYaml(content);
            default -> throw new InvalidParameterException("file format not supported: " + fileFormat);
        };
    }
}
