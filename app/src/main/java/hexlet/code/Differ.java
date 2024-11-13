package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

public class Differ {
    private static final String DEFAULT_FORMATTER = "stylish";

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, DEFAULT_FORMATTER);
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        var data1 = Parser.getData(readFile(filepath1));
        var data2 = Parser.getData(readFile(filepath2));

        var keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        var diff = new LinkedHashMap<String, Map<String, String>>();
        keys.forEach(key -> {
            var node = new HashMap<String, String>();
            if (!data1.containsKey(key)) {
                node.put("action", "added");
                node.put("value", data2.get(key).toString());
            } else if (!data2.containsKey(key)) {
                node.put("action", "deleted");
                node.put("value", data1.get(key).toString());
            } else if (data1.get(key).equals(data2.get(key))) {
                node.put("action", "unchanged");
                node.put("value", data1.get(key).toString());
            } else {
                node.put("action", "changed");
                node.put("value1", data1.get(key).toString());
                node.put("value2", data2.get(key).toString());
            }
            diff.put(key, node);
        });

        return Formatter.format(diff, formatName);
    }

    private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return readFile(path);
    }

    private static String readFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }
}
