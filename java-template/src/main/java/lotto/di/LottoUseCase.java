package lotto.di;

import lotto.domain.LottoResultDto;
import lotto.domain.LottosDto;

public interface LottoUseCase {

    void buy(BuyLottoCommand buyLottoCommand);

    LottosDto getAllLottos();

    void changeWinningNumbers(WinningNumbersCommand winningNumbersCommand);


    LottoResultDto calculateStatistics();


}
