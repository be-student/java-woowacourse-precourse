package lotto.domain;

import static lotto.config.LottoConstants.LOTTO_END_INCLUSIVE;
import static lotto.config.LottoConstants.LOTTO_LENGTH;
import static lotto.config.LottoConstants.LOTTO_START_INCLUSIVE;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

class Lotto {

    private final List<Integer> numbers;

    Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException();
        }
        if (!isNumbersInRange(numbers)) {
            throw new IllegalArgumentException("범위 바깥의 수가 들어왔습니다");
        }
        if (!isUnique(numbers)) {
            throw new IllegalArgumentException("중복된 수가 들어왔습니다");
        }
    }

    private boolean isUnique(List<Integer> numbers) {
        return new HashSet<>(numbers).size() == LOTTO_LENGTH;
    }

    private boolean isNumbersInRange(List<Integer> numbers) {
        return numbers.stream()
                .allMatch(this::isInRange);
    }

    private boolean isInRange(int lottoNumber) {
        return LOTTO_START_INCLUSIVE <= lottoNumber && lottoNumber <= LOTTO_END_INCLUSIVE;
    }

    List<Integer> toDto() {
        return Collections.unmodifiableList(numbers);
    }

    boolean contains(int other) {
        return numbers.contains(other);
    }
}
