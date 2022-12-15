package bridge.adapter.in.client;

import java.util.List;

public class PrintResultCommand {

    private final List<List<String>> result;
    private final boolean cleared;
    private final int count;

    public PrintResultCommand(List<List<String>> result, boolean cleared, int count) {
        this.result = result;
        this.cleared = cleared;
        this.count = count;
    }

    public boolean isCleared() {
        return cleared;
    }

    public int getCount() {
        return count;
    }

    public List<List<String>> getResult() {
        return result;
    }
}
