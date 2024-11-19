package hexled.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.parsers.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonParserTest {
    @Test
    public void testParse() throws JsonProcessingException {
        Assertions.assertEquals(
                Map.of("key1", 1, "key2", "2"),
                JsonParser.parse("{\"key1\":1, \"key2\":\"2\"}")
        );
    }
}
