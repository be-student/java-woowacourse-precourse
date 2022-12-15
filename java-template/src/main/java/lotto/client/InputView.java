package lotto.client;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lotto.di.BuyLottoCommand;
import lotto.util.NumberValidator;

public class InputView {

    private static final Pattern WINNING_NUMBER = Pattern.compile("^\\d(,\\d)+$");

    private static final String FORMAT_ERROR_MESSAGE = "형식이 올바르지 않습니다";
    private static final String ASK_AMOUNT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String ASK_WINNING_MESSAGE = "\n당첨 번호를 입력해 주세요.";

    BuyLottoCommand getAmountFromClient() {
        System.out.println(ASK_AMOUNT_MESSAGE);
        return new BuyLottoCommand(Console.readLine());
    }

    List<Integer> askCorrectNumber() {
        System.out.println(ASK_WINNING_MESSAGE);
        String input = Console.readLine();
        validateCorrectNumber(input);
        return Arrays.stream(input.split(","))
                .map(NumberValidator::toInt)
                .collect(Collectors.toList());
    }

    private void validateCorrectNumber(String input) {
        if (!isRightFormat(input)) {
            throw new IllegalArgumentException(FORMAT_ERROR_MESSAGE);
        }
    }

    private boolean isRightFormat(String input) {
        return WINNING_NUMBER.matcher(input).matches();
    }

    int askBonusNumber() {
        System.out.println("\n보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        validateBonusNumber(input);
        return NumberValidator.toInt(input);
    }

    private void validateBonusNumber(String input) {
        if (!NumberValidator.isNumber(input)) {
            throw new IllegalArgumentException("숫자로 이루어져야 합니다");
        }
    }
}
