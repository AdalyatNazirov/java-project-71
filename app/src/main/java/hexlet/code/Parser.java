package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;

import java.security.InvalidParameterException;
import java.util.Map;

/**
 * Provides utility methods for parsing JSON and YAML data into Java maps.
 */
public class Parser {

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
            case "json" -> JsonParser.parse(content);
            case "yaml", "yml" -> YamlParser.parse(content);
            default -> throw new InvalidParameterException("file format not supported: " + fileFormat);
        };
    }
}
