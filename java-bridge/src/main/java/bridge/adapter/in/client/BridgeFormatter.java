package bridge.adapter.in.client;

import java.util.List;

public class BridgeFormatter {

    private final StringBuilder result = new StringBuilder();

    public BridgeFormatter(List<List<String>> result) {
        formatOneLine(result.get(1));
        formatOneLine(result.get(0));
    }

    private void formatOneLine(List<String> line) {
        result.append("[ ");
        result.append(String.join(" | ", line));
        result.append(" ]\n");
    }

    public String formatResult() {
        return result.toString();
    }
}
