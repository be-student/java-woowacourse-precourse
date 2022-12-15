package baseball.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Ball 클래스")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BallTest {

    @Test
    void Ball_의_범위안에_숫자가_들어오면_enum_을_잘_가져온다() {
        assertThat(Ball.from(0)).isEqualTo(Ball.ZERO);
    }

    @Test
    void Ball_의_범위밖에_숫자가_들어오면_IEA_예외가_발생한다() {
        Throwable expected = catchThrowable(() -> Ball.from(-1));

        assertThat(expected).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("숫자는 0부터 3까지만 가능합니다");
    }
}
