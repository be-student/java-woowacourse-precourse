package lotto.domain;

import java.util.List;

public class WinningNumbers {

    private final CorrectNumber correctNumbers;
    private final BonusNumber bonusNumber;

    public WinningNumbers(List<Integer> correctNumbers, int bonusNumber) {
        validate(correctNumbers, bonusNumber);
        this.correctNumbers = new CorrectNumber(correctNumbers);
        this.bonusNumber = new BonusNumber(bonusNumber);
    }

    private void validate(List<Integer> correctNumbers, int bonusNumber) {
        if (correctNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("중복된 값이 들어왔습니다");
        }
    }

    LottoResult calculateResult(Lotto target) {
        int sameCount = correctNumbers.calculateSameCount(target);
        boolean hasBonus = bonusNumber.hasBonus(target);
        return LottoResult.getMaxPrize(sameCount, hasBonus);
    }
}
