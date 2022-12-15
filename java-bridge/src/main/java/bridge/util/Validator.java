package bridge.util;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern BIGGER_THAN_ZERO = Pattern.compile("^\\d+$");
    private static final String NOT_NUMBER_MESSAGE = "숫자로 변환할 수 없습니다";

    private Validator() {
    }

    public static boolean isStringGreaterOrEqualThanZero(String input) {
        if (input == null) {
            return false;
        }
        return BIGGER_THAN_ZERO.matcher(input).matches();
    }

    public static int toInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }
}
