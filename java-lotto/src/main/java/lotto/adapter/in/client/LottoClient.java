package lotto.adapter.in.client;

import java.util.List;
import lotto.application.port.in.BuyLottoCommand;
import lotto.application.port.in.LottoUseCase;
import lotto.application.port.in.WinningNumbersCommand;
import lotto.domain.LottoResultDto;

public class LottoClient {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoUseCase lottoUseCase;

    public LottoClient(InputView inputView, OutputView outputView, LottoUseCase lottoUseCase) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoUseCase = lottoUseCase;
    }

    public void run() {
        buyLotto();
        getAllLottos();
        changeWinningNumbers();
        printStatistic();
    }

    private void buyLotto() {
        BuyLottoCommand buyLottoCommand = inputView.getAmountFromClient();
        lottoUseCase.buy(buyLottoCommand);
    }

    private void getAllLottos() {
        outputView.printAllLottos(lottoUseCase.getAllLottos());
    }

    private void changeWinningNumbers() {
        List<Integer> correctNumber = inputView.askCorrectNumber();
        int bonusNumber = inputView.askBonusNumber();
        lottoUseCase.changeWinningNumbers(new WinningNumbersCommand(correctNumber, bonusNumber));
    }

    private void printStatistic() {
        LottoResultDto lottoResultDto = lottoUseCase.calculateStatistics();
        outputView.printStatistics(lottoResultDto);
    }
}
