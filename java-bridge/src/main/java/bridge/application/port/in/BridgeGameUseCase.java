package bridge.application.port.in;

import java.util.List;

public interface BridgeGameUseCase {

    void createBridge(CreateBridgeCommand createBridgeCommand);

    void move(MoveCommand moveCommand);

    void retry();

    List<List<String>> result();

    int triedCount();

    boolean isCleared();

    boolean isFinished();
}
