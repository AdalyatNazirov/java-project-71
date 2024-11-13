package hexlet.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDiff {
    private String key;
    private String action;
    private String valueOld;
    private String valueNew;

    public NodeDiff(String key) {
        this.setKey(key);
    }
}
