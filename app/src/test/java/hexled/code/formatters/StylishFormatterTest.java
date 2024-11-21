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
        node.setValue2("value");
        node.setAction("added");

        Assertions.assertEquals("""
                {
                  + key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenRemoved() {

        var node = new NodeDiff();
        node.setValue1("value");
        node.setAction("deleted");

        Assertions.assertEquals("""
                {
                  - key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenChanged() {

        var node = new NodeDiff();
        node.setValue1("valueOld");
        node.setValue2("valueNew");
        node.setAction("changed");

        Assertions.assertEquals("""
                {
                  - key: valueOld
                  + key: valueNew
                }""", StylishFormatter.format(Map.of("key", node)));
    }

    @Test
    public void testFormatStylishWhenUnchanged() {

        var node = new NodeDiff();
        node.setValue1("value");
        node.setValue2("value");
        node.setAction("unchanged");

        Assertions.assertEquals("""
                {
                    key: value
                }""", StylishFormatter.format(Map.of("key", node)));
    }
}
