package bridge.application.port.in;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("CreateBridgeCommand 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CreateBridgeCommandTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"-1", " a"})
    void 생성시_0보다_작은_숫자거나_숫자로_변환할_수_없다면_예외(String input) {
        Throwable expected = catchThrowable(() -> new CreateBridgeCommand(input));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 생성시_입력이_0_이상이면_숫자로_변환된다() {
        assertThat(new CreateBridgeCommand("1").getBridgeLength()).isEqualTo(1);
    }
}
