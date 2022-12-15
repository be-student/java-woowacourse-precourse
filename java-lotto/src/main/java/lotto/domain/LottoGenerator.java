package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoGenerator {

    private final LottoNumbersGenerator lottoNumbersGenerator;

    public LottoGenerator(LottoNumbersGenerator lottoNumbersGenerator) {
        this.lottoNumbersGenerator = lottoNumbersGenerator;
    }

    public List<Lotto> generate(int count) {
        return Stream.generate(lottoNumbersGenerator::generate)
                .limit(count)
                .map(Lotto::new)
                .collect(Collectors.toList());
    }
}
