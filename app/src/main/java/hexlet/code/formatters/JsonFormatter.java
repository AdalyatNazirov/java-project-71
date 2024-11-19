package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.NodeDiff;

import java.util.Map;

/**
 * Provides a utility method for formatting a map of node differences as a JSON string.
 */
public class JsonFormatter {

    public static final ObjectMapper MAPPER_JSON = new ObjectMapper(); // create once, reuse

    /**
     * Formats a map of node differences as a JSON string.
     *
     * @param data the map of node differences to format
     * @return the JSON formatted string representation of the data
     * @throws JsonProcessingException if an error occurs during JSON processing
     */
    public static String format(Map<String, NodeDiff> data) throws JsonProcessingException {
        return MAPPER_JSON.writeValueAsString(data);
    }
}
