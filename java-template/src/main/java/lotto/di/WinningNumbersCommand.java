package lotto.di;

import java.util.List;

public class WinningNumbersCommand {

    private static final String NULL_MESSAGE = "입력에는 null 이 들어올 수 없습니다";
    private final List<Integer> correctNumbers;
    private final int bonusNumber;

    public WinningNumbersCommand(List<Integer> correctNumbers, int bonusNumber) {
        validate(correctNumbers);
        this.correctNumbers = correctNumbers;
        this.bonusNumber = bonusNumber;
    }

    private void validate(List<Integer> correctNumbers) {
        if (correctNumbers == null) {
            throw new IllegalArgumentException(NULL_MESSAGE);
        }
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public List<Integer> getCorrectNumbers() {
        return correctNumbers;
    }
}
