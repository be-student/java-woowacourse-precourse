package bridge.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Validator 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class ValidatorTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" a", "world ", "2 "})
    void toInt_메서드는_int_로_변환하지_못하면_IllegalArgumentException_을_발생시킨다(String input) {
        Throwable expected = catchThrowable(() -> Validator.toInt(input));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void toInt_메서드는_int_로_변환한다() {
        assertThat(Validator.toInt("-3232342")).isEqualTo(-3232342);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" a", "world ", "2 ", "-1"})
    void isStringNaturalNumber_메서드는_숫자가_자연수가_아니면_false_를_반환한다(String input) {
        assertFalse(() -> Validator.isStringGreaterOrEqualThanZero(input));
    }

    @Test
    void isStringNaturalNumber_메서드는_변환_가능_하다면_true_를_반환한다() {
        assertTrue(() -> Validator.isStringGreaterOrEqualThanZero("3232342"));
        assertTrue(() -> Validator.isStringGreaterOrEqualThanZero("0"));
    }
}
