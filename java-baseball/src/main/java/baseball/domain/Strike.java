package baseball.domain;

import java.util.Arrays;

enum Strike {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3);

    private static final String OVER_RANGE_MESSAGE = "숫자는 0부터 3까지만 가능합니다";
    private final int strikeCount;

    Strike(int strikeCount) {
        this.strikeCount = strikeCount;
    }

    public static Strike from(int strikeCount) {
        return Arrays.stream(Strike.values())
                .filter(it -> it.strikeCount == strikeCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(OVER_RANGE_MESSAGE));
    }
}
