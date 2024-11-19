package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.Map;

/**
 * Provides utility method for formatting and processing data related to node differences.
 * This class offers a way to transform data into various string representations,
 * making it easier to work with and display the information.
 */
public class Formatter {

    /**
     * Formats the given data into a string representation according to the specified format.
     *
     * @param data   the data to be formatted, represented as a map of node differences
     * @param format the desired output format (one of "stylish", "plain", or "json")
     * @return the formatted string representation of the data
     * @throws JsonProcessingException  if an error occurs during JSON processing
     * @throws IllegalArgumentException if the specified format is not supported
     */
    public static String format(Map<String, NodeDiff> data, String format) throws JsonProcessingException {
        return switch (format.toLowerCase()) {
            case "stylish" -> StylishFormatter.format(data);
            case "plain" -> PlainFormatter.format(data);
            case "json" -> JsonFormatter.format(data);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }
}
