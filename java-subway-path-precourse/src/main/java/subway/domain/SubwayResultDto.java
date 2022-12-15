package subway.domain;

import java.util.List;

public class SubwayResultDto {

    private final List<String> stations;
    private final List<Line> lines;

    public SubwayResultDto(List<String> stations, List<Line> lines) {
        this.stations = stations;
        this.lines = lines;
    }

    public List<Line> getLines() {
        return lines;
    }

    public List<String> getStations() {
        return stations;
    }
}
