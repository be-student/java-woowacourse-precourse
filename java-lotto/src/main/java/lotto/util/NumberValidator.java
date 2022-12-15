package lotto.util;

import java.util.regex.Pattern;

public class Validator {

    private static final Pattern NUMBER = Pattern.compile("^\\d+$");

    private Validator() {
    }

    public static boolean isNumber(String input) {
        return NUMBER.matcher(input).matches();
    }

    public static int toInt(String input) {
        
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환할 수 없습니다");
        }
    }

    public static long toLong(String input) {
        try {
            return Long.parseLong(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자로 변환할 수 없습니다");
        }
    }
}
