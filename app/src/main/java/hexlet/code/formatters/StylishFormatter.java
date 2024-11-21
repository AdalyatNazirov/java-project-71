package hexlet.code.formatters;

import hexlet.code.NodeDiff;

import java.util.Map;

/**
 * Provides a utility method for formatting a map of node differences as a stylish string.
 */
public class StylishFormatter {

    /**
     * Formats a map of node differences as a Stylish string.
     *
     * @param data the map of node differences to format
     * @return the stylish formatted string representation of the data
     */
    public static String format(Map<String, NodeDiff> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, NodeDiff> entry : data.entrySet()) {
            String key = entry.getKey();
            NodeDiff diff = entry.getValue();
            sb.append("  ");
            switch (diff.getAction()) {
                case "added":
                    sb.append("+ ").append(key).append(": ").append(getJsonValue(diff.getValue2()));
                    break;
                case "deleted":
                    sb.append("- ").append(key).append(": ").append(getJsonValue(diff.getValue1()));
                    break;
                case "unchanged":
                    sb.append("  ").append(key).append(": ").append(getJsonValue(diff.getValue2()));
                    break;
                case "changed":
                    sb.append("- ").append(key).append(": ").append(getJsonValue(diff.getValue1())).append("\n");
                    sb.append("  ");
                    sb.append("+ ").append(key).append(": ").append(getJsonValue(diff.getValue2()));
                    break;
                default:
                    break;
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    private static String getJsonValue(Object value) {
        return String.valueOf(value);
    }
}
