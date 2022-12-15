package lotto.domain;

import java.util.List;

public class LottosDto {

    private final List<List<Integer>> lottos;

    LottosDto(List<List<Integer>> lottos) {
        this.lottos = lottos;
    }

    public List<List<Integer>> getLottos() {
        return lottos;
    }
}
