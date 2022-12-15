package baseball.domain;

import java.util.Arrays;

enum Ball {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private static final String OVER_RANGE_MESSAGE = "숫자는 0부터 3까지만 가능합니다";
    private final int ballCount;

    Ball(int ballCount) {
        this.ballCount = ballCount;
    }

    public static Ball from(int ballCount) {
        return Arrays.stream(Ball.values())
                .filter(it -> it.ballCount == ballCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(OVER_RANGE_MESSAGE));
    }
}
