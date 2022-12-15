package bridge.domain;

import java.util.ArrayList;
import java.util.List;

public class History {

    private List<List<String>> history;
    private int triedCount = 0;

    public History() {
        init();
    }

    private void init() {
        history = new ArrayList<>();
        history.add(new ArrayList<>());
        history.add(new ArrayList<>());
    }

    public void retry() {
        triedCount++;
        init();
    }

    public int getTriedCount() {
        return triedCount;
    }

    public void move(BridgePosition nextPosition, Bridge bridge) {
        put(nextPosition, bridge.isEqualAtIndex(historySize(), nextPosition));
    }

    private void put(BridgePosition nextPosition, boolean isSuccess) {
        int index = nextPosition.toCode();
        int other = 1 - index;
        if (isSuccess) {
            history.get(index).add("O");
            history.get(other).add(" ");
            return;
        }
        history.get(index).add("X");
        history.get(other).add(" ");
    }

    public List<List<String>> toDto() {
        List<List<String>> result = List.of(new ArrayList<>(), new ArrayList<>());
        result.get(0).addAll(history.get(0));
        result.get(1).addAll(history.get(1));
        return result;
    }

    public boolean isFinished(Bridge target) {
        if (historySize() == target.size()) {
            return true;
        }
        return history.stream()
                .anyMatch(it -> it.contains("X"));
    }

    public boolean isCleared(Bridge target) {
        if (historySize() != target.size()) {
            return false;
        }
        return history.stream()
                .noneMatch(it -> it.contains("X"));
    }

    private int historySize() {
        return history.size();
    }
}
