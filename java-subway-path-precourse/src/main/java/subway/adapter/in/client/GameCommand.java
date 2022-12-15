package subway.adapter.in.client;

import java.util.Arrays;

public enum GameCommand {
    QUIT("Q"),
    START("1");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand fromCommand(String command) {
        return Arrays.stream(GameCommand.values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 1 또는 Q만 가능합니다"));
    }

    public String getCommand() {
        return command;
    }
}
