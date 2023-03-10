package lotto.di;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoResult;
import lotto.domain.LottoResultDto;
import lotto.domain.Lottos;
import lotto.domain.LottosDto;
import lotto.domain.Money;
import lotto.domain.WinningNumbers;

public class LottoUseCaseImpl implements LottoUseCase {

    private final LottoGenerator lottoGenerator;
    private Lottos lottos;
    private WinningNumbers winningNumbers;

    public LottoUseCaseImpl(LottoGenerator lottoGenerator) {
        this.lottoGenerator = lottoGenerator;
    }

    @Override
    public void buy(BuyLottoCommand buyLottoCommand) {
        Long amount = buyLottoCommand.getAmount();
        Money money = new Money(amount);
        lottos = new Lottos(lottoGenerator.generate(money.ableToBuyLottos()));
    }

    @Override
    public LottosDto getAllLottos() {
        return lottos.toDto();
    }

    @Override
    public void changeWinningNumbers(WinningNumbersCommand winningNumbersCommand) {
        int bonusNumber = winningNumbersCommand.getBonusNumber();
        List<Integer> correctNumbers = winningNumbersCommand.getCorrectNumbers();
        winningNumbers = new WinningNumbers(correctNumbers, bonusNumber);
    }

    @Override
    public LottoResultDto calculateStatistics() {
        List<LottoResult> lottoResults = lottos.calculateResults(winningNumbers);
        Map<LottoResult, Integer> result = new EnumMap<>(LottoResult.class);
        lottoResults
                .forEach(it -> result.put(it, result.getOrDefault(it, 0) + 1));
        return new LottoResultDto(result, lottoResults.size());
    }
}
