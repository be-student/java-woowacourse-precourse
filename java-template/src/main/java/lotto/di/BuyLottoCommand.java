package lotto.di;


import static lotto.util.NumberValidator.isNumber;

import lotto.util.NumberValidator;

public class BuyLottoCommand {

    private static final String INPUT_NULL_MESSAGE = "입력에 null 이 있으면 안됩니다";
    private static final String NOT_NUMBER_MESSAGE = "금액은 숫자로 이루어져야 합니다";
    private final Long amount;

    public BuyLottoCommand(String input) {
        validate(input);
        amount = NumberValidator.toLong(input);
    }

    private void validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException(INPUT_NULL_MESSAGE);
        }
        if (!isNumber(input)) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }

    public Long getAmount() {
        return amount;
    }
}
