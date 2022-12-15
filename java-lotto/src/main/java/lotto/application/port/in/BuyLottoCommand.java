package lotto.application.port.in;


import static lotto.util.Validator.isNumber;

import lotto.util.Validator;

public class BuyLottoCommand {

    private static final String INPUT_NULL_MESSAGE = "입력에 null 이 있으면 안됩니다";
    private static final String NOT_NUMBER_MESSAGE = "금액은 숫자로 이루어져야 합니다";
    private final Long amount;

    public BuyLottoCommand(String input) {
        validate(input);
        amount = Validator.toLong(input);
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
