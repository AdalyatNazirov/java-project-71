package hexled.code;

import hexlet.code.Formatter;
import hexlet.code.NodeDiff;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class FormatterTest {

    @Test
    public void testFormatStylishWhenAdded() {

        var node = new NodeDiff("key");
        node.setValueNew("value");
        node.setAction("added");

        Assertions.assertEquals("""
                {
                \t+ key: value
                }""", Formatter.format(Map.of("key", node), "stylish"));
    }

    @Test
    public void testFormatStylishWhenRemoved() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setAction("deleted");

        Assertions.assertEquals("""
                {
                \t- key: value
                }""", Formatter.format(Map.of("key", node), "stylish"));
    }

    @Test
    public void testFormatStylishWhenChanged() {

        var node = new NodeDiff("key");
        node.setValueOld("valueOld");
        node.setValueNew("valueNew");
        node.setAction("changed");

        Assertions.assertEquals("""
                {
                \t- key: valueOld
                \t+ key: valueNew
                }""", Formatter.format(Map.of("key", node), "stylish"));
    }

    @Test
    public void testFormatStylishWhenUnchanged() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setValueNew("value");
        node.setAction("unchanged");

        Assertions.assertEquals("""
                {
                \t  key: value
                }""", Formatter.format(Map.of("key", node), "stylish"));
    }

    @Test
    public void testFormatPlainWhenAdded() {

        var node = new NodeDiff("key");
        node.setValueNew("value");
        node.setAction("added");

        Assertions.assertEquals(
                "Property 'key' was added with value: 'value'",
                Formatter.format(Map.of("key", node), "plain"));
    }

    @Test
    public void testFormatPlainWhenRemoved() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setAction("deleted");

        Assertions.assertEquals(
                "Property 'key' was removed",
                Formatter.format(Map.of("key", node), "plain"));
    }

    @Test
    public void testFormatPlainWhenChanged() {

        var node = new NodeDiff("key");
        node.setValueOld("valueOld");
        node.setValueNew("valueNew");
        node.setAction("changed");

        Assertions.assertEquals(
                "Property 'key' was updated. From 'valueOld' to 'valueNew'",
                Formatter.format(Map.of("key", node), "plain"));
    }

    @Test
    public void testFormatPlainWhenUnchanged() {

        var node = new NodeDiff("key");
        node.setValueOld("value");
        node.setValueNew("value");
        node.setAction("unchanged");

        Assertions.assertEquals(
                "",
                Formatter.format(Map.of("key", node), "plain"));
    }

    @Test
    public void throwErrorIfFormatNotSupported() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                Formatter.format(Map.of(), "json"));
    }
}
