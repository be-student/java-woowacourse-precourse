package baseball.adapter.in.game;

import baseball.application.port.in.GamePlayCommand;
import baseball.application.port.in.GameUseCase;
import baseball.domain.GameResultDto;

public final class GameClient {

    private final ClientInputView inputView;
    private final GameUseCase gameUseCase;
    private final ClientOutputView outputView;

    public GameClient(GameUseCase gameUseCase, ClientInputView inputView, ClientOutputView outputView) {
        this.gameUseCase = gameUseCase;
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void playGame() {
        outputView.printInputMessage();
        ClientChoice clientChoice = ClientChoice.RETRY;
        while (clientChoice != ClientChoice.QUIT) {
            gameUseCase.reset();
            playOneGame();
            clientChoice = askClientToQuitOrRetry();
        }
    }


    private void playOneGame() {
        while (!gameUseCase.finished()) {
            GamePlayCommand gamePlayCommand = askPlayerNumber();
            GameResultDto gameResultDto = gameUseCase.play(gamePlayCommand);
            outputView.printResult(gameResultDto);
        }
    }

    private GamePlayCommand askPlayerNumber() {
        outputView.printNumberMessage();
        String input = inputView.readLine();
        return new GamePlayCommand(input);
    }

    private ClientChoice askClientToQuitOrRetry() {
        outputView.printRetryMessage();
        String input = inputView.readLine();
        return ClientChoice.from(input);
    }
}
