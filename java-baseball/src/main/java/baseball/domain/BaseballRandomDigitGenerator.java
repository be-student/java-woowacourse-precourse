package baseball.domain;

import static baseball.domain.BaseballGameConstant.BASEBALL_MAX_DIGIT;
import static baseball.domain.BaseballGameConstant.BASEBALL_MIN_DIGIT;

import camp.nextstep.edu.missionutils.Randoms;

public final class BaseballRandomDigitGenerator implements BaseballDigitGenerator {

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(BASEBALL_MIN_DIGIT, BASEBALL_MAX_DIGIT);
    }
}
