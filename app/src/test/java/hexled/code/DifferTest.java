package hexled.code;

import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.nio.file.Files;
import java.nio.file.Path;

public final class DifferTest {
    @ParameterizedTest
    @ValueSource(strings = {"yaml", "json"})
    public void testDiffForFile(String extension) throws Exception {
        var path = Path.of(getFilePath("result_stylish.txt"));
        var expected = Files.readString(path);

        var result = Differ.generate(
                getFilePath("file1." + extension),
                getFilePath("file2." + extension));

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yaml", "json"})
    public void testStylishFormat(String extension) throws Exception {
        var path = Path.of(getFilePath("result_stylish.txt"));
        var expected = Files.readString(path);

        var result = Differ.generate(
                getFilePath("file1." + extension),
                getFilePath("file2." + extension),
                "stylish");

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yaml", "json"})
    public void testPlainFormat(String extension) throws Exception {
        var path = Path.of(getFilePath("result_plain.txt"));
        var expected = Files.readString(path);

        var result = Differ.generate(
                getFilePath("file1." + extension),
                getFilePath("file2." + extension),
                "plain");

        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yaml", "json"})
    public void testJsonFormat(String extension) throws Exception {
        var path = Path.of(getFilePath("result_json.txt"));
        var expected = Files.readString(path);

        var result = Differ.generate(
                getFilePath("file1." + extension),
                getFilePath("file2." + extension),
                "json");

        Assertions.assertEquals(expected, result);
    }

    private static String getFilePath(String fileName) {
        var resource = DifferTest.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return resource.getPath();
    }
}
