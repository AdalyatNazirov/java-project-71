package hexlet.code.formatters;

import hexlet.code.NodeDiff;

import java.util.Map;

public class StylishFormatter {
    public static String format(Map<String, NodeDiff> data) {
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
}
