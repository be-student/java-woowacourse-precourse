package bridge;

import static bridge.domain.BridgeGameConstants.BRIDGE_MAX_LENGTH;
import static bridge.domain.BridgeGameConstants.BRIDGE_MIN_LENGTH;

import bridge.domain.BridgePosition;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 다리의 길이를 입력 받아서 다리를 생성해주는 역할을 한다.
 */
public class BridgeMaker {

    private static final String OVER_RANGE_MESSAGE = "숫자의 범위는 3부터 20까지만 가능합니다";
    private final BridgeNumberGenerator bridgeNumberGenerator;

    public BridgeMaker(BridgeNumberGenerator bridgeNumberGenerator) {
        this.bridgeNumberGenerator = bridgeNumberGenerator;
    }

    /**
     * @param size 다리의 길이
     * @return 입력받은 길이에 해당하는 다리 모양. 위 칸이면 "U", 아래 칸이면 "D"로 표현해야 한다.
     */
    public List<String> makeBridge(int size) {
        validate(size);
        return Stream.generate(this::createBridgeWithSingleCode)
                .limit(size)
                .map(BridgePosition::getPosition)
                .collect(Collectors.toList());
    }

    private void validate(int size) {
        if (!isInRange(size)) {
            throw new IllegalArgumentException(OVER_RANGE_MESSAGE);
        }
    }

    private boolean isInRange(int size) {
        return BRIDGE_MIN_LENGTH <= size && size <= BRIDGE_MAX_LENGTH;
    }

    private BridgePosition createBridgeWithSingleCode() {
        int code = bridgeNumberGenerator.generate();
        return BridgePosition.createWithCode(code);
    }
}
