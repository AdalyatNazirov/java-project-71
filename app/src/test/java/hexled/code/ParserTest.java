package hexled.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

public final class ParserTest {
    @Test
    public void testParseJson() throws JsonProcessingException {
        Assertions.assertEquals(
                Map.of("key1", 1, "key2", "2"),
                Parser.parse("{\"key1\":1, \"key2\":\"2\"}", "json")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"yaml", "yml"})
    public void testParseYaml(String ext) throws JsonProcessingException {
        Assertions.assertEquals(
                Map.of("key1", 1, "key2", "2"),
                Parser.parse("key1: 1\nkey2: \"2\"", ext)
        );
    }
}
