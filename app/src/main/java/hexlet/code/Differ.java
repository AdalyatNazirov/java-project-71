package hexlet.code;

public class Differ {
    private static final String DEFAULT_FORMATTER = "stylish";

    public static String generate(String filepath1, String filepath2) {
        return generate(filepath1, filepath2, DEFAULT_FORMATTER);
    }

    public static String generate(String filepath1, String filepath2, String formatName) {
        System.out.println(filepath1);
        System.out.println(filepath2);
        return "";
    }
}
