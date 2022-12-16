package lotto.util;

import java.util.regex.Pattern;

public class NumberValidator {

    private static final Pattern NUMBER = Pattern.compile("^-?\\d+$");

    private NumberValidator() {
    }

    public static boolean isNumber(String input) {
        if (input == null) {
            return false;
        }
        return NUMBER.matcher(input).matches();
    }

    public static int toInt(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(input + "을 숫자로 변환할 수 없습니다");
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(input + "은 숫자 범위를 초과했습니다");
        }
    }

    public static long toLong(String input) {
        if (!isNumber(input)) {
            throw new IllegalArgumentException(input + "을 숫자로 변환할 수 없습니다");
        }
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(input + "은 숫자 범위를 초과했습니다");
        }
    }
}
