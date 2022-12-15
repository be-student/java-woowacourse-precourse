package bridge.application.port.in;

public class MoveCommand {

    private final String move;

    public MoveCommand(String move) {
        validate(move);
        this.move = move;
    }

    private void validate(String move) {
        if (move == null) {
            throw new IllegalArgumentException("입력에 null 이 오면 안됩니다");
        }
    }

    public String getMove() {
        return move;
    }
}
