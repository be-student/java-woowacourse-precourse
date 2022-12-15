package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("BridgePosition 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BridgePositionTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"hello", "U "})
    void UD_가_아닌_다른_문자로_생성을_시도하면_예외_발생(String input) {
        Throwable expected = catchThrowable(() -> BridgePosition.createWithPosition(input));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void UD로_생성하면_잘_생성됨() {
        assertThat(BridgePosition.createWithPosition("U")).isEqualTo(BridgePosition.UP);
        assertThat(BridgePosition.createWithPosition("D")).isEqualTo(BridgePosition.DOWN);
    }

    @ParameterizedTest
    @ValueSource(ints = {-123, 2342})
    void 생성시_0_1_이_아닌_다른_숫자는_예외(int input) {
        Throwable expected = catchThrowable(() -> BridgePosition.createWithCode(input));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }


    @Test
    void 생성시_0과_1로_생성하면_잘_생성됨() {
        assertThat(BridgePosition.createWithCode(1)).isEqualTo(BridgePosition.UP);
        assertThat(BridgePosition.createWithCode(0)).isEqualTo(BridgePosition.DOWN);
    }
}
