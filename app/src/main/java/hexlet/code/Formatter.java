package hexlet.code;

import java.util.Map;

public class Formatter {

    public static String format(Map<String, NodeDiff> data, String format) {
        return switch (format.toLowerCase()) {
            case "stylish" -> formatStylish(data);
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
                    sb.append("+ ").append(key).append(": ").append(diff.getValueNew());
                    break;
                case "deleted":
                    sb.append("- ").append(key).append(": ").append(diff.getValueOld());
                    break;
                case "unchanged":
                    sb.append("  ").append(key).append(": ").append(diff.getValueNew());
                    break;
                case "changed":
                    sb.append("- ").append(key).append(": ").append(diff.getValueOld()).append("\n\t");
                    sb.append("+ ").append(key).append(": ").append(diff.getValueNew());
                    break;
                default:
                    break;
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
