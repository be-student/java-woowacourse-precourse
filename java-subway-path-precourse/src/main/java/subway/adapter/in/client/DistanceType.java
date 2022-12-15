package subway.adapter.in.client;

import java.util.Arrays;

public enum DistanceType {
    DISTANCE("1"),
    TIME("2"),
    BACK("B");


    private final String command;

    DistanceType(String command) {
        this.command = command;
    }

    public static DistanceType fromCommand(String command) {
        return Arrays.stream(DistanceType.values())
                .filter(it -> it.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력은 1,2,B만 가능합니다"));
    }

    public String getCommand() {
        return command;
    }
}
