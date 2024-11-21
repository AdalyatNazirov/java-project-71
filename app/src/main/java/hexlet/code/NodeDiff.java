package hexlet.code;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeDiff {
    private String action;
    private Object value1;
    private Object value2;
}
