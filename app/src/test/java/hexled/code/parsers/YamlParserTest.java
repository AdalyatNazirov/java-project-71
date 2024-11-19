package hexled.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parsers.YamlParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class YamlParserTest {
    @Test
    public void testParse() throws JsonProcessingException {
        Assertions.assertEquals(
                Map.of("key1", 1, "key2", "2"),
                YamlParser.parse("key1: 1\nkey2: \"2\"")
        );
    }
}
