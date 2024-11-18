package hexled.code.formatters;

import hexlet.code.NodeDiff;
import hexlet.code.formatters.PlainFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class PlainFormatterTest {
    @Test
    public void testFormatPlainWhenAdded() {

        var node = new NodeDiff("key");
        node.setValueNew("value");
        node.setAction("added");

        Assertions.assertEquals(
                "Property 'key' was added with value: 'value'",
                PlainFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatPlainWhenRemoved() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setAction("deleted");

        Assertions.assertEquals(
                "Property 'key' was removed",
                PlainFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatPlainWhenChanged() {

        var node = new NodeDiff("key");
        node.setValueOld("valueOld");
        node.setValueNew("valueNew");
        node.setAction("changed");

        Assertions.assertEquals(
                "Property 'key' was updated. From 'valueOld' to 'valueNew'",
                PlainFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatPlainWhenUnchanged() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setValueNew("value");
        node.setAction("unchanged");

        Assertions.assertEquals(
                "",
                PlainFormatter.format(Map.of("key", node)));
    }
}
