package hexled.code.formatters;

import hexlet.code.NodeDiff;
import hexlet.code.formatters.StylishFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class StylishFormatterTest {

    @Test
    public void testFormatStylishWhenAdded() {

        var node = new NodeDiff();
        node.setValueNew("value");
        node.setAction("added");

        Assertions.assertEquals("""
                {
                \t+ key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenRemoved() {

        var node = new NodeDiff();
        node.setValueOld("value");
        node.setAction("deleted");

        Assertions.assertEquals("""
                {
                \t- key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenChanged() {

        var node = new NodeDiff();
        node.setValueOld("valueOld");
        node.setValueNew("valueNew");
        node.setAction("changed");

        Assertions.assertEquals("""
                {
                \t- key: valueOld
                \t+ key: valueNew
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenUnchanged() {

        var node = new NodeDiff();
        node.setValueOld("value");
        node.setValueNew("value");
        node.setAction("unchanged");

        Assertions.assertEquals("""
                {
                \t  key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }
}
