package baseball.adapter.in.game;

import java.util.Arrays;
import java.util.Objects;

enum ClientChoice {
    RETRY("1"),
    QUIT("2");

    private static final String NOT_FOUND_ERROR = "1,2만 입력하셔야 합니다";
    private final String value;

    ClientChoice(String value) {
        this.value = value;
    }

    public static ClientChoice from(String value) {
        return Arrays.stream(ClientChoice.values())
                .filter(it -> Objects.equals(it.value, value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NOT_FOUND_ERROR));
    }
}
