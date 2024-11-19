package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, NodeDiff> data, String format) throws JsonProcessingException {
        return switch (format.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(data);
            case "plain" -> PlainFormatter.format(data);
            case "json" -> JsonFormatter.format(data);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
