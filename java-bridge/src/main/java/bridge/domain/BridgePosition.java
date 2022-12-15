package bridge.domain;

import java.util.Arrays;

public enum BridgePosition {
    UP(1, "U"),
    DOWN(0, "D");

    private final int generatedCode;
    private final String position;

    BridgePosition(int generatedCode, String position) {
        this.generatedCode = generatedCode;
        this.position = position;
    }

    public static BridgePosition createWithCode(int code) {
        return Arrays.stream(BridgePosition.values())
                .filter(it -> it.generatedCode == code)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("코드는 1 또는 0만 가능합니다"));
    }

    public static BridgePosition createWithPosition(String position) {
        return Arrays.stream(BridgePosition.values())
                .filter(it -> it.position.equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("코드는 1 또는 0만 가능합니다"));
    }

    public String getPosition() {
        return position;
    }

    public int toCode() {
        return generatedCode;
    }
}
