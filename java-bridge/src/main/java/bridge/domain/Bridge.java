package bridge.domain;

import static bridge.domain.BridgeGameConstants.BRIDGE_MAX_LENGTH;
import static bridge.domain.BridgeGameConstants.BRIDGE_MIN_LENGTH;

import java.util.List;
import java.util.stream.Collectors;

public class Bridge {

    private static final String NULL_MESSAGE = "bridge 에 null 이 들어오면 안 됩니다";
    private static final String OVER_RANGE_MESSAGE = "숫자의 범위는 3부터 20까지만 가능합니다";
    private final List<BridgePosition> bridgePositions;

    public Bridge(List<String> bridge) {
        validate(bridge);
        bridgePositions = bridge.stream()
                .map(BridgePosition::createWithPosition)
                .collect(Collectors.toList());
    }

    private void validate(List<String> bridge) {
        if (bridge == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (!isInRange(bridge.size())) {
            throw new IllegalArgumentException(OVER_RANGE_MESSAGE);
        }
    }

    private boolean isInRange(int size) {
        return BRIDGE_MIN_LENGTH <= size && size <= BRIDGE_MAX_LENGTH;
    }

    public boolean isEqualAtIndex(int index, BridgePosition position) {
        return bridgePositions.get(index).equals(position);
    }

    public int size() {
        return bridgePositions.size();
    }
}
