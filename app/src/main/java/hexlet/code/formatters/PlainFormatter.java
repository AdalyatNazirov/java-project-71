package hexlet.code.formatters;

import hexlet.code.NodeDiff;

import java.util.Map;

public class PlainFormatter {
    public static String format(Map<String, NodeDiff> data) {
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
