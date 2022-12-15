package baseball;

import baseball.adapter.in.game.ClientInputView;
import baseball.adapter.in.game.ClientOutputView;
import baseball.adapter.in.game.GameClient;
import baseball.application.port.in.GameUseCase;
import baseball.application.service.BaseballGame;
import baseball.domain.BaseballNumberGenerator;
import baseball.domain.BaseballRandomDigitGenerator;

public class Application {

    public static void main(String[] args) {
        GameClient gameClient = new GameClient(
                generateGameUseCase(), generateClientInputView(), generateClientOutputView());
        gameClient.playGame();
    }

    private static ClientOutputView generateClientOutputView() {
        return new ClientOutputView();
    }

    private static GameUseCase generateGameUseCase() {
        return new BaseballGame(baseballNumberGenerator());
    }

    private static BaseballNumberGenerator baseballNumberGenerator() {
        return new BaseballNumberGenerator(new BaseballRandomDigitGenerator());
    }

    private static ClientInputView generateClientInputView() {
        return new ClientInputView();
    }
}
