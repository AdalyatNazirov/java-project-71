package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class DiffProcessor {
    public static Map<String, NodeDiff> compareMaps(Map<String, Object> data1, Map<String, Object> data2) {
        var keys = new TreeSet<>(data1.keySet());
        keys.addAll(data2.keySet());

        var diff = new LinkedHashMap<String, NodeDiff>();
        keys.forEach(key -> {
            var node = new NodeDiff();
            if (!data1.containsKey(key)) {
                node.setAction("added");
            } else if (!data2.containsKey(key)) {
                node.setAction("deleted");
            } else if (Objects.equals(data1.get(key), data2.get(key))) {
                node.setAction("unchanged");
            } else {
                node.setAction("changed");
            }
            node.setValue1(data1.get(key));
            node.setValue2(data2.get(key));
            diff.put(key, node);
        });
        return diff;
    }
}
