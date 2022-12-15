package baseball.application.port.in;

import baseball.domain.GameResultDto;

public interface GameUseCase {

    GameResultDto play(GamePlayCommand gamePlayCommand);

    void reset();

    boolean finished();
}
