package lotto.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("NumberValidator 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class NumberValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" 1234", "23-42", "hello"})
    void toInt_메서드는_숫자로_변환할_수_없으면_IEA_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> NumberValidator.toInt(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(input + "을 숫자로 변환할 수 없습니다");
    }

    @Test
    void toInt_메서드는_int_범위를_초과하면_IEA_예외를_발생시킨다() {
        assertThatThrownBy(() -> NumberValidator.toInt("99999999999999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("99999999999999" + "은 숫자 범위를 초과했습니다");
    }

    @Test
    void toInt_메서드는_숫자로_변환한다() {
        assertThat(NumberValidator.toInt("2342")).isEqualTo(2342);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" 1234", "23-42", "hello"})
    void toLong_메서드는_숫자로_변환할_수_없으면_IEA_예외를_발생시킨다(String input) {
        assertThatThrownBy(() -> NumberValidator.toLong(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(input + "을 숫자로 변환할 수 없습니다");
    }

    @Test
    void toLong_메서드는_long_범위를_초과하면_IEA_예외를_발생시킨다() {
        assertThatThrownBy(() -> NumberValidator.toLong("9999999999999999999999999999999999"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("9999999999999999999999999999999999" + "은 숫자 범위를 초과했습니다");
    }

    @Test
    void toLong_메서드는_숫자로_변환한다() {
        assertThat(NumberValidator.toLong("2342")).isEqualTo(2342);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" 2342", "help"})
    void isNumber_메서드는_숫자가_아니라면_false_를_반환한다(String input) {
        assertFalse(NumberValidator.isNumber(input));
    }

    @Test
    void isNumber_메서드는_숫자라면_true_를_반환한다() {
        assertTrue(NumberValidator.isNumber("2342"));
    }
}
