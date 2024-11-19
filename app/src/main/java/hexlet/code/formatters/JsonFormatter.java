package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.NodeDiff;

import java.util.Map;

public class JsonFormatter {

    public static final ObjectMapper MAPPER_JSON = new ObjectMapper(); // create once, reuse

    public static String format(Map<String, NodeDiff> data) throws JsonProcessingException {
        return MAPPER_JSON.writeValueAsString(data);
    }
}
