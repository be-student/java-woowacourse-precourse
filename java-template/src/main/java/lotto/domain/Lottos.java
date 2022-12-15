package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public LottosDto toDto() {

        return new LottosDto(lottos.stream()
                .map(Lotto::toDto)
                .collect(Collectors.toList()));
    }

    public List<LottoResult> calculateResults(WinningNumbers winningNumbers) {
        return lottos.stream()
                .map(winningNumbers::calculateResult)
                .collect(Collectors.toList());
    }
}
