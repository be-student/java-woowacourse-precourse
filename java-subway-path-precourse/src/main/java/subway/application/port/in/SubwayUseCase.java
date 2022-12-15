package subway.application.port.in;

import subway.domain.SubwayResultDto;

public interface SubwayUseCase {

    SubwayResultDto calculateResult(MinimumDistanceCommand minimumDistanceCommand);
}
