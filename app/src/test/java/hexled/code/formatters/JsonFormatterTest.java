package hexled.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.NodeDiff;
import hexlet.code.formatters.JsonFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class JsonFormatterTest {
    @Test
    public void testFormat() throws JsonProcessingException {
        var diff = new NodeDiff();
        diff.setAction("added");
        diff.setValue2("value");
        var data = Map.of("key", diff);

        Assertions.assertEquals(
                "{\"key\":{\"action\":\"added\",\"valueOld\":null,\"valueNew\":\"value\"}}",
                JsonFormatter.format(data));
    }
}
