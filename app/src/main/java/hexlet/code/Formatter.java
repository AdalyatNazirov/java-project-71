package hexlet.code;

import java.util.Map;

public class Formatter {


    public static String format(Map<String, Map<String, String>> data, String format) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
            String key = entry.getKey();
            Map<String, String> diff = entry.getValue();
            sb.append("\t");
            switch (diff.get("action")) {
                case "added":
                    sb.append("+ ").append(key).append(": ").append(diff.get("value"));
                    break;
                case "deleted":
                    sb.append("- ").append(key).append(": ").append(diff.get("value"));
                    break;
                case "unchanged":
                    sb.append("  ").append(key).append(": ").append(diff.get("value"));
                    break;
                case "changed":
                    sb.append("- ").append(key).append(": ").append(diff.get("value1")).append("\n\t");
                    sb.append("+ ").append(key).append(": ").append(diff.get("value2"));
                    break;
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
