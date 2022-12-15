package lotto.domain;

import java.util.Map;

public class LottoResultDto {

    private final Map<LottoResult, Integer> result;
    private final int totalCount;

    public LottoResultDto(Map<LottoResult, Integer> result, int totalCount) {
        this.result = result;
        this.totalCount = totalCount;
    }

    public Map<LottoResult, Integer> getResult() {
        return result;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
