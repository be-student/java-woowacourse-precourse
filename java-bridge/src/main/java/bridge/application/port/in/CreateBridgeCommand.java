package bridge.application.port.in;

import static bridge.util.Validator.isStringGreaterOrEqualThanZero;

import bridge.util.Validator;

public class CreateBridgeCommand {

    private static final String INVALID_LENGTH_MESSAGE = "길이는 0이상이어야 합니다";

    private final int bridgeLength;

    public CreateBridgeCommand(String bridgeLength) {
        validate(bridgeLength);
        this.bridgeLength = Validator.toInt(bridgeLength);
    }

    private void validate(String bridgeLength) {
        if (!isStringGreaterOrEqualThanZero(bridgeLength)) {
            throw new IllegalArgumentException(INVALID_LENGTH_MESSAGE);
        }
    }

    public int getBridgeLength() {
        return bridgeLength;
    }
}
