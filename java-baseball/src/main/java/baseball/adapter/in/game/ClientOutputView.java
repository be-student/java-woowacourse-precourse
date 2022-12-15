package baseball.adapter.in.game;

import static baseball.domain.BaseballGameConstant.BASEBALL_LENGTH;

import baseball.domain.GameResultDto;

public final class ClientOutputView {

    private static final String THREE_STRIKE_MESSAGE = "3스트라이크";
    private static final String STRIKE_MESSAGE = "스트라이크";
    private static final String RETRY_MESSAGE = "게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.";
    private static final String BALL_MESSAGE = "볼 ";
    private static final String END_MESSAGE = "3개의 숫자를 모두 맞히셨습니다! 게임 종료";
    private static final String INPUT_MESSAGE = "숫자를 입력해주세요 : ";
    private static final String INITIAL_MESSAGE = "숫자 야구 게임을 시작합니다.";
    private static final String NOTHING_MESSAGE = "낫싱";

    private static final int NOTHING = 0;

    void printResult(GameResultDto gameResultDto) {
        if (isAllStrike(gameResultDto)) {
            printCompleteMessage();
            return;
        }
        if (isNothing(gameResultDto)) {
            printNothingMessage();
            return;
        }
        printBallAndStrike(gameResultDto);
    }


    private boolean isAllStrike(GameResultDto gameResultDto) {
        return gameResultDto.getStrike() == BASEBALL_LENGTH;
    }

    private void printCompleteMessage() {
        System.out.println(THREE_STRIKE_MESSAGE);
        System.out.println(END_MESSAGE);
    }

    private boolean isNothing(GameResultDto gameResultDto) {
        return gameResultDto.getStrike() == NOTHING && gameResultDto.getBall() == NOTHING;
    }

    private void printNothingMessage() {
        System.out.println(NOTHING_MESSAGE);
    }

    private void printBallAndStrike(GameResultDto gameResultDto) {
        System.out.print(gameResultDto.getBall() + BALL_MESSAGE);
        System.out.print(gameResultDto.getStrike() + STRIKE_MESSAGE);
        System.out.println();
    }

    void printRetryMessage() {
        System.out.println(RETRY_MESSAGE);
    }

    void printNumberMessage() {
        System.out.print(INPUT_MESSAGE);
    }


    void printInputMessage() {
        System.out.println(INITIAL_MESSAGE);
    }
}
