package bridge.adapter.in.client;

import bridge.application.port.in.BridgeGameUseCase;
import bridge.application.port.in.CreateBridgeCommand;
import bridge.application.port.in.MoveCommand;
import java.util.List;
import java.util.function.Supplier;

public class BridgeGameClient {

    private final InputView inputView;
    private final OutputView outputView;
    private final BridgeGameUseCase bridgeGameUseCase;

    public BridgeGameClient(
            InputView inputView,
            OutputView outputView,
            BridgeGameUseCase bridgeGameUseCase
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.bridgeGameUseCase = bridgeGameUseCase;
    }

    public void playBridgeGame() {
        printStartMessage();
        repeat(this::setUpBridgeGame);
        GameCommand command = GameCommand.RETRY;
        while (command != GameCommand.QUIT) {
            bridgeGameUseCase.retry();
            play();
            command = askRetry();
        }
        printResult();
    }

    private void printResult() {
        List<List<String>> result = bridgeGameUseCase.result();
        boolean cleared = bridgeGameUseCase.isCleared();
        int triedCount = bridgeGameUseCase.triedCount();
        outputView.printResult(new PrintResultCommand(result, cleared, triedCount));
    }

    private GameCommand askRetry() {
        if (bridgeGameUseCase.isCleared()) {
            return GameCommand.QUIT;
        }
        return repeat(inputView::readGameCommand);
    }


    private void setUpBridgeGame() {
        CreateBridgeCommand createBridgeCommand = inputView.readBridgeSize();
        bridgeGameUseCase.createBridge(createBridgeCommand);
    }

    private void printStartMessage() {
        outputView.printStartMessage();
    }

    private void play() {
        while (!bridgeGameUseCase.isFinished()) {
            MoveCommand moveCommand = repeat(inputView::readMoving);
            bridgeGameUseCase.move(moveCommand);
            List<List<String>> result = bridgeGameUseCase.result();
            outputView.printMap(result);
        }
    }

    private void repeat(Runnable inputReader) {
        try {
            inputReader.run();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            repeat(inputReader);
        }
    }

    private <T> T repeat(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeat(inputReader);
        }
    }
}
