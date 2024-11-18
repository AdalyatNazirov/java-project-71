package hexlet.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDiff {
    private String key;
    private String action;
    private Object valueOld;
    private Object valueNew;

    public NodeDiff(String key) {
        this.setKey(key);
    }
}
