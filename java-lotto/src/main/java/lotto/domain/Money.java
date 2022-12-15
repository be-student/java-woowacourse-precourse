package lotto.domain;

public class Money {

    private static final String DIVISION_FAIL_MESSAGE = "1000으로 금액이 나누어 떨어져야합니다";

    private final long amount;

    public Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException(DIVISION_FAIL_MESSAGE);
        }
    }

    public int ableToBuyLottos() {
        return (int) (amount / 1000);
    }
}
