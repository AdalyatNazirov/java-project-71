package hexlet.code;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, NodeDiff> data, String format) {
        return switch (format.toLowerCase()) {
            case "stylish" -> formatStylish(data);
            case "plain" -> formatPlain(data);
            default -> throw new IllegalArgumentException("Unsupported format: " + format);
        };
    }


    private static String formatStylish(Map<String, NodeDiff> data) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, NodeDiff> entry : data.entrySet()) {
            String key = entry.getKey();
            NodeDiff diff = entry.getValue();
            sb.append("\t");
            switch (diff.getAction()) {
                case "added":
                    sb.append("+ ").append(key).append(": ").append(getJsonValue(diff.getValueNew()));
                    break;
                case "deleted":
                    sb.append("- ").append(key).append(": ").append(getJsonValue(diff.getValueOld()));
                    break;
                case "unchanged":
                    sb.append("  ").append(key).append(": ").append(getJsonValue(diff.getValueNew()));
                    break;
                case "changed":
                    sb.append("- ").append(key).append(": ").append(getJsonValue(diff.getValueOld())).append("\n\t");
                    sb.append("+ ").append(key).append(": ").append(getJsonValue(diff.getValueNew()));
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

    private static String formatPlain(Map<String, NodeDiff> data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, NodeDiff> entry : data.entrySet()) {
            String key = entry.getKey();
            NodeDiff diff = entry.getValue();
            boolean newLine = true;
            switch (diff.getAction()) {
                case "added":
                    sb.append("Property '").append(key)
                            .append("' was added with value: ")
                            .append(getPlainValue(diff.getValueNew()));
                    break;
                case "deleted":
                    sb.append("Property '").append(key).append("' was removed");
                    break;
                case "changed":
                    sb.append("Property '").append(key).append("' was updated.")
                            .append(" From ").append(getPlainValue(diff.getValueOld()))
                            .append(" to ").append(getPlainValue(diff.getValueNew()));
                    break;
                default:
                    newLine = false;
                    break;
            }
            if (newLine) {
                sb.append("\n");
            }
        }
        return sb.toString().trim();
    }

    private static String getPlainValue(Object value) {
        if (value == null) {
            return "null";
        } else if (value instanceof String) {
            return "'" + value + "'";
        } else if (isPrimitiveWrapper(value)) {
            return String.valueOf(value);
        }

        return "[complex value]";
    }

    public static boolean isPrimitiveWrapper(Object obj) {
        return obj instanceof Integer
                || obj instanceof Double
                || obj instanceof Float
                || obj instanceof Long
                || obj instanceof Short
                || obj instanceof Byte
                || obj instanceof Character
                || obj instanceof Boolean;
    }
}
