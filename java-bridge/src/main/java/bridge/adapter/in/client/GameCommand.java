package bridge.adapter.in.client;

import java.util.Arrays;

public enum GameCommand {
    RETRY("R"),
    QUIT("Q");

    private static final String CODE_ERROR_MESSAGE = "코드에는 R과 Q만 사용할 수 있습니다";
    private final String code;

    GameCommand(String code) {
        this.code = code;
    }

    public static GameCommand from(String code) {
        return Arrays.stream(GameCommand.values())
                .filter(it -> it.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(CODE_ERROR_MESSAGE));
    }
}
