package lotto.domain;

import static lotto.config.LottoConstants.LOTTO_END_INCLUSIVE;
import static lotto.config.LottoConstants.LOTTO_LENGTH;
import static lotto.config.LottoConstants.LOTTO_START_INCLUSIVE;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomLottoNumbersGenerator implements LottoNumbersGenerator {

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(LOTTO_START_INCLUSIVE, LOTTO_END_INCLUSIVE, LOTTO_LENGTH);
    }
}
