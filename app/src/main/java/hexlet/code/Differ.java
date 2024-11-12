package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Differ {
    private static final String DEFAULT_FORMATTER = "stylish";

    public static String generate(String filepath1, String filepath2) throws Exception {
        return generate(filepath1, filepath2, DEFAULT_FORMATTER);
    }

    public static String generate(String filepath1, String filepath2, String formatName) throws Exception {
        System.out.println(filepath1);
        System.out.println(Parser.getData(readFile(filepath1)));
        System.out.println(filepath2);
        System.out.println(Parser.getData(readFile(filepath2)));
        return "";
    }

    private static String readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        return readFile(path);
    }

    private static String readFile(Path filePath) throws IOException {
        return Files.readString(filePath);
    }
}
