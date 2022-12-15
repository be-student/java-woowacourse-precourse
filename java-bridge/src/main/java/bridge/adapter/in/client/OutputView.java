package bridge.adapter.in.client;

import java.util.List;

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
public class OutputView {

    public static final String RESULT_TITLE_MESSAGE = "최종 게임 결과";
    public static final String TRIED_COUNT_MESSAGE = "총 시도한 횟수: %d\n";
    public static final String SUCCESS_OR_FAILURE_MESSAGE = "게임 성공 여부: ";
    public static final String SUCCESS_MESSAGE = "성공";
    public static final String FAILURE_MESSAGE = "실패";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String START_MESSAGE = "다리 건너기 게임을 시작합니다.\n";

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printMap(List<List<String>> result) {
        BridgeFormatter bridgeFormatter = new BridgeFormatter(result);
        System.out.println(bridgeFormatter.formatResult());
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     * <p>
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    public void printResult(PrintResultCommand printResultCommand) {
        System.out.println(RESULT_TITLE_MESSAGE);
        printMap(printResultCommand.getResult());
        printSuccessOrFailure(printResultCommand.isCleared());
        System.out.printf(TRIED_COUNT_MESSAGE, printResultCommand.getCount());
    }

    private void printSuccessOrFailure(boolean isCleared) {
        System.out.print(SUCCESS_OR_FAILURE_MESSAGE);
        if (isCleared) {
            System.out.println(SUCCESS_MESSAGE);
            return;
        }
        System.out.println(FAILURE_MESSAGE);

    }

    public void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public void printError(String errorMessage) {
        System.out.println(ERROR_PREFIX + errorMessage);
    }
}
