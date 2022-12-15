package lotto.client;

import java.util.List;
import lotto.di.BuyLottoCommand;
import lotto.di.LottoUseCase;
import lotto.di.WinningNumbersCommand;
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
