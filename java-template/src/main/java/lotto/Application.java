package lotto;

import lotto.client.InputView;
import lotto.client.LottoClient;
import lotto.client.OutputView;
import lotto.di.LottoUseCaseImpl;
import lotto.domain.LottoGenerator;
import lotto.domain.LottoNumbersGenerator;
import lotto.domain.RandomLottoNumbersGenerator;

public class Application {

    public static void main(String[] args) {
        try {
            LottoClient lottoClient = getLottoClient();
            lottoClient.run();
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR]" + e.getMessage());
        }

    }

    private static LottoClient getLottoClient() {
        return new LottoClient(getInputView(), getOutputView(),
                getLottoApplication());
    }

    private static InputView getInputView() {
        return new InputView();
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }

    private static LottoUseCaseImpl getLottoApplication() {
        return new LottoUseCaseImpl(getLottoGenerator());
    }

    private static LottoGenerator getLottoGenerator() {
        return new LottoGenerator(getLottoNumberGenerator());
    }

    private static LottoNumbersGenerator getLottoNumberGenerator() {
        return new RandomLottoNumbersGenerator();
    }
}
