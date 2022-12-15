package baseball.domain;

import static baseball.domain.BaseballGameConstant.BASEBALL_LENGTH;
import static baseball.domain.BaseballGameConstant.BASEBALL_MAX_DIGIT;
import static baseball.domain.BaseballGameConstant.BASEBALL_MIN_DIGIT;

import java.util.List;
import java.util.stream.IntStream;

public final class BaseballNumber {

    private static final String NOT_VALID_LENGTH_MESSAGE = "숫자의 길이가 3이 아닙니다";
    private static final String NOT_UNIQUE_MESSAGE = "숫자에 중복이 있습니다";
    private static final String OVER_RANGE_MESSAGE = "9가 넘는 숫자가 들어올 수 없습니다";
    private static final String BELOW_RANGE_MESSAGE = "1보다 작은 숫자가 들어올 수 없습니다";

    private final List<Integer> numbers;

    public BaseballNumber(List<Integer> numbers) {
        this.numbers = numbers;
        validate();
    }

    private void validate() {
        if (isNotValidLength()) {
            throw new IllegalArgumentException(NOT_VALID_LENGTH_MESSAGE);
        }
        if (isNotUnique()) {
            throw new IllegalArgumentException(NOT_UNIQUE_MESSAGE);
        }
        if (isOverRange()) {
            throw new IllegalArgumentException(OVER_RANGE_MESSAGE);
        }
        if (isBelowRange()) {
            throw new IllegalArgumentException(BELOW_RANGE_MESSAGE);
        }
    }

    private boolean isNotValidLength() {
        return numbers.size() != BASEBALL_LENGTH;
    }

    private boolean isNotUnique() {
        return numbers.stream()
                .distinct()
                .count() != BASEBALL_LENGTH;
    }

    private boolean isOverRange() {
        return numbers.stream()
                .anyMatch(it -> it > BASEBALL_MAX_DIGIT);
    }

    private boolean isBelowRange() {
        return numbers.stream()
                .anyMatch(it -> it < BASEBALL_MIN_DIGIT);
    }

    public GameResultDto calculateScore(BaseballNumber other) {
        int strikeCount = calculateStrike(other);
        int ballCount = calculateContains(other) - strikeCount;
        return new GameResultDto(strikeCount, ballCount);
    }

    private int calculateContains(BaseballNumber other) {
        return (int) numbers.stream()
                .filter(other::contains)
                .count();
    }

    private int calculateStrike(BaseballNumber other) {
        return (int) IntStream.range(0, BASEBALL_LENGTH).
                filter(index -> other.equalAtIndex(index, numbers.get(index)))
                .count();
    }

    private boolean equalAtIndex(int index, int key) {
        return numbers.get(index) == key;
    }

    private boolean contains(int key) {
        return numbers.contains(key);
    }
}
