package hexled.code;

import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DifferTest {
    private static String compareResults;

    @BeforeAll
    public static void setUp() throws IOException {
        var path = Path.of(getFilePath("result.txt"));
        compareResults = Files.readString(path);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yaml", "json"})
    public void testDiffForFile(String extension) throws Exception {
        var result = Differ.generate(
                getFilePath("file1." + extension),
                getFilePath("file2." + extension));

        Assertions.assertEquals(compareResults, result);
    }

    private static String getFilePath(String fileName) {
        var resource = DifferTest.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + fileName);
        }
        return resource.getPath();
    }
}
