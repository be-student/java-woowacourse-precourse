package lotto;

import lotto.adapter.in.client.InputView;
import lotto.adapter.in.client.LottoClient;
import lotto.adapter.in.client.OutputView;
import lotto.application.service.LottoApplication;
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

    private static LottoApplication getLottoApplication() {
        return new LottoApplication(getLottoGenerator());
    }

    private static LottoGenerator getLottoGenerator() {
        return new LottoGenerator(getLottoNumberGenerator());
    }

    private static LottoNumbersGenerator getLottoNumberGenerator() {
        return new RandomLottoNumbersGenerator();
    }
}
