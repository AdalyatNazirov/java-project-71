package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, NodeDiff> data, String format) {
        return switch (format.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(data);
            case "plain" -> PlainFormatter.format(data);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
