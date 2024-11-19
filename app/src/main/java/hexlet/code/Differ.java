package hexlet.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Compares two files and generates a string representation of their differences.
 */
public class Differ {
    private static final String DEFAULT_FORMATTER = "stylish";

    /**
     * Generates a string representation of the differences between two files using the default formatter.
     *
     * @param filepath1 the path to the first file
     * @param filepath2 the path to the second file
     * @return a string representation of the differences between the two files
     * @throws Exception if an error occurs during file reading or parsing
     */
    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, DEFAULT_FORMATTER);
    }

    /**
     * Generates a string representation of the differences between two files using the specified formatter.
     *
     * @param filepath1  the path to the first file
     * @param filepath2  the path to the second file
     * @param formatName the name of the formatter to use (e.g. "stylish", "plain", "json")
     * @return a string representation of the differences between the two files
     * @throws Exception if an error occurs during file reading or parsing
     */
    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        var data1 = getData(filepath1);
        var data2 = getData(filepath2);

        var keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        var diff = new LinkedHashMap<String, NodeDiff>();
        keys.forEach(key -> {
            var node = new NodeDiff();
            if (!data1.containsKey(key)) {
                node.setAction("added");
            } else if (!data2.containsKey(key)) {
                node.setAction("deleted");
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                node.setAction("unchanged");
            } else {
                node.setAction("changed");
            }
            node.setValueOld(data1.get(key));
            node.setValueNew(data2.get(key));
            diff.put(key, node);
        });

        return Formatter.format(diff, formatName);
    }

    private static Map<String, Object> getData(String filepath) throws Exception {

        var content = readFile(filepath);
        return Parser.parse(content, getFileExtension(filepath));
    }

    private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return readFile(path);
    }

    private static String readFile(Path filePath) throws IOException {
        if (Files.notExists(filePath)) {
            throw new FileNotFoundException(filePath.toString());
        }

        return Files.readString(filePath);
    }

    private static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return ""; // No extension found
        }

        return fileName.substring(dotIndex + 1);
    }

}
