package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class Parser {
    public static final ObjectMapper mapper = new ObjectMapper(); // create once, reuse

    public static Map<String, Object> getData(String content) throws Exception {
        return parse(content);
    }

    private static Map<String, Object> parse(String content) throws JsonProcessingException {
        return mapper.readValue(content, new TypeReference<>() {
        });
    }
}
