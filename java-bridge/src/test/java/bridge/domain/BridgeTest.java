package bridge.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

@DisplayName("Bridge 클래스")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BridgeTest {

    private static Stream<List<String>> generateSource() {
        return Stream.of(
                null,
                List.of("U", "U"),
                List.of("asdf", "asdf", "U")
        );
    }

    @ParameterizedTest
    @MethodSource("generateSource")
    void 생성시_잘못된_값이_들어오면_IEA_예외가_발생한다(List<String> input) {
        Throwable expected = catchThrowable(() -> new Bridge(input));
        assertThat(expected).isInstanceOf(IllegalArgumentException.class);
    }
}
