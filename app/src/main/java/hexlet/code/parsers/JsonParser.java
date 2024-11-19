package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Provides utility methods for parsing JSON data into Java maps.
 */
public class JsonParser {
    public static final ObjectMapper MAPPER_JSON = new ObjectMapper(); // create once, reuse

    /**
     * Parses a string into a Java map based on the specified file format.
     *
     * @param content the string to parse
     * @return a Java map representing the parsed data
     * @throws JsonProcessingException   if an error occurs during parsing
     */
    public static Map<String, Object> parse(String content) throws JsonProcessingException {
        return MAPPER_JSON.readValue(content, new TypeReference<>() {
        });
    }
}
