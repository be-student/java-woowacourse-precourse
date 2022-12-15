package baseball.application.service;

import static baseball.domain.BaseballGameConstant.BASEBALL_LENGTH;

import baseball.application.port.in.GamePlayCommand;
import baseball.application.port.in.GameUseCase;
import baseball.domain.BaseballNumber;
import baseball.domain.BaseballNumberGenerator;
import baseball.domain.GameResultDto;

public final class BaseballGame implements GameUseCase {

    private final BaseballNumberGenerator generator;
    private BaseballNumber computerNumber;
    private BaseballNumber playerNumber;

    public BaseballGame(BaseballNumberGenerator generator) {
        this.generator = generator;
    }

    @Override
    public GameResultDto play(GamePlayCommand gamePlayCommand) {
        playerNumber = new BaseballNumber(gamePlayCommand.getPlayerInput());
        return computerNumber.calculateScore(playerNumber);
    }

    @Override
    public void reset() {
        computerNumber = generator.generate();
        playerNumber = null;
    }

    @Override
    public boolean finished() {
        if (isNotInitialized()) {
            return false;
        }
        return computerNumber.calculateScore(playerNumber)
                .getStrike() == BASEBALL_LENGTH;
    }

    private boolean isNotInitialized() {
        return playerNumber == null || computerNumber == null;
    }
}
