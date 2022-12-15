package subway.adapter.in.client;

import java.util.function.Supplier;
import subway.application.port.in.MinimumDistanceCommand;
import subway.application.port.in.SubwayUseCase;
import subway.domain.SubwayResultDto;

public class SubwayClient {

    private final SubwayUseCase subwayUseCase;
    private final InputView inputView;
    private final OutputView outputView;

    public SubwayClient(SubwayUseCase subwayUseCase, InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.subwayUseCase = subwayUseCase;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            GameCommand playerInput = askRetry();
            if (playerInput == GameCommand.QUIT) {
                return;
            }
            repeat(this::calculateDistance);
        }
    }

    private void calculateDistance() {
        DistanceType distanceType = askType();
        if (distanceType == DistanceType.BACK) {
            return;
        }
        String startStation = inputView.askStartStation();
        String endStation = inputView.askEndStation();
        MinimumDistanceCommand minimumDistanceCommand = new MinimumDistanceCommand(
                distanceType.getCommand(), startStation, endStation);
        SubwayResultDto subwayResultDto = subwayUseCase.calculateResult(minimumDistanceCommand);
        outputView.printResult(subwayResultDto);
    }

    private GameCommand askRetry() {
        return repeat(inputView::askRetry);
    }

    private <T> T repeat(Supplier<T> playerInput) {
        try {
            return playerInput.get();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return repeat(playerInput);
        }
    }

    private void repeat(Runnable run) {
        try {
            run.run();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            repeat(run);
        }
    }

    private DistanceType askType() {
        return repeat(inputView::askType);
    }
}
