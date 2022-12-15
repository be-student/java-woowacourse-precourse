package bridge.application.port.in;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayName("MoveCommand 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class MoveCommandTest {

    @Test
    void 생성시_null_이_들어오면_예외() {
        Throwable expected = catchThrowable(() -> new MoveCommand(null));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }
}
