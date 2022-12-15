package lotto.client;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.LottoResult;
import lotto.domain.LottoResultDto;
import lotto.domain.LottosDto;

public class OutputView {

    private static final String LOTTO_SEPARATOR = ", ";
    private static final String LOTTO_START = "[";
    private static final String LOTTO_END = "]\n";

    void printAllLottos(LottosDto lottoResultDto) {
        System.out.printf("%n%d개를 구매했습니다.%n", lottoResultDto.getLottos().size());
        List<List<Integer>> lottos = lottoResultDto.getLottos();
        lottos.forEach(this::printOneLotto);
    }

    private void printOneLotto(List<Integer> lotto) {
        String printableLotto = lotto.stream()
                .sorted(Comparator.naturalOrder())
                .map(String::valueOf)
                .collect(Collectors.joining(LOTTO_SEPARATOR));
        System.out.print(LOTTO_START);
        System.out.printf(printableLotto);
        System.out.print(LOTTO_END);
    }

    void printStatistics(LottoResultDto lottoResultDto) {
        System.out.println("당첨 통계");
        System.out.println("---");
        printStatistic(lottoResultDto.getResult());
        printRate(lottoResultDto);
        System.out.println("---");
    }


    private void printStatistic(Map<LottoResult, Integer> lottoResult) {
        printFifth(lottoResult.getOrDefault(LottoResult.FIFTH_PRIZE, 0));
        printFourth(lottoResult.getOrDefault(LottoResult.FOURTH_PRIZE, 0));
        printThird(lottoResult.getOrDefault(LottoResult.THIRD_PRIZE, 0));
        printTwo(lottoResult.getOrDefault(LottoResult.SECOND_PRIZE, 0));
        printOne(lottoResult.getOrDefault(LottoResult.FIRST_PRIZE, 0));
    }

    private void printFifth(int result) {
        System.out.printf("3개 일치 (5,000원) - %d개%n", result);
    }

    private void printFourth(int result) {
        System.out.printf("4개 일치 (50,000원) - %d개%n", result);
    }

    private void printThird(int result) {
        System.out.printf("5개 일치 (1,500,000원) - %d개%n", result);
    }

    private void printTwo(int result) {
        System.out.printf("5개 일치, 보너스 볼 일치 (30,000,000원) - %d개%n", result);
    }

    private void printOne(int result) {
        System.out.printf("6개 일치 (2,000,000,000원) - %d개%n", result);
    }

    private void printRate(LottoResultDto lottoResultDto) {
        long totalAmount = lottoResultDto.getTotalCount() * 1000L;
        long getAllPrice = Arrays.stream(LottoResult.values()).mapToLong(
                lottoResult -> lottoResult.getPrize() * (long) lottoResultDto.getResult()
                        .getOrDefault(lottoResult, 0)).reduce(0L, Long::sum);
        double rate = (double) getAllPrice / totalAmount * 100;
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }
}
