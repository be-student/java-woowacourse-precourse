package lotto.domain;

import java.util.Arrays;
import java.util.Comparator;

public enum LottoResult {
    FIRST_PRIZE(6, false, 2_000_000_000),
    SECOND_PRIZE(5, true, 30_000_000),
    THIRD_PRIZE(5, false, 1_500_500),
    FOURTH_PRIZE(4, false, 50_000),
    FIFTH_PRIZE(3, false, 5_000),
    NOTHING(0, false, 0);

    private final int sameNumbers;
    private final boolean requiredBonusNumber;
    private final int prize;

    LottoResult(int sameNumbers, boolean requiredBonusNumber, int prize) {
        this.requiredBonusNumber = requiredBonusNumber;
        this.sameNumbers = sameNumbers;
        this.prize = prize;
    }

    public static LottoResult getMaxPrize(int sameNumbers, boolean requiredBonusNumber) {
        return Arrays.stream(LottoResult.values())
                .filter(it -> it.sameNumbers <= sameNumbers)
                .filter(it -> isRequiredBonusNumber(it.requiredBonusNumber, requiredBonusNumber))
                .max(Comparator.comparingInt(it -> it.prize))
                .orElse(LottoResult.NOTHING);
    }

    private static boolean isRequiredBonusNumber(boolean required, boolean current) {
        if (!required) {
            return true;
        }
        return current;
    }

    public int getPrize() {
        return prize;
    }
}
