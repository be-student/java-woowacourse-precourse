package baseball.application.port.in;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class GamePlayCommand {

    private static final String NULL_MESSAGE = "입력에 null 이 있으면 안 됩니다";
    private static final String NOT_NUMBER_MESSAGE = "숫자로 변환할 수 없습니다";
    private static final Pattern NUMBER = Pattern.compile("^\\d+$");

    private final List<Integer> playerInput;

    public GamePlayCommand(String input) {
        validate(input);
        playerInput = Arrays.stream(input.split(""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    private void validate(String input) {
        if (input == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
        if (isNotNumber(input)) {
            throw new IllegalArgumentException(NOT_NUMBER_MESSAGE);
        }
    }

    private boolean isNotNumber(String input) {
        return !NUMBER.matcher(input).matches();
    }


    public List<Integer> getPlayerInput() {
        return playerInput;
    }
}

