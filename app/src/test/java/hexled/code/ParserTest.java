package hexled.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class ParserTest {
    @Test
    public void testParse() throws JsonProcessingException {
        Assertions.assertEquals(
                Map.of("a", 1),
                Parser.parse("{\"a\":1}")
        );
    }
}
