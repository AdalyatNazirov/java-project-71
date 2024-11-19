package hexled.code;

import hexlet.code.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FormatterTest {

    @Test
    public void throwErrorIfFormatNotSupported() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                Formatter.format(Map.of(), "xml"));
    }
}
