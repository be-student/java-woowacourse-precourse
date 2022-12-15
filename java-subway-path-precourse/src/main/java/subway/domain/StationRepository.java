package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StationRepository {

    private static final List<Station> stations = new ArrayList<>();

    private StationRepository() {
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static void deleteAll() {
        stations.clear();
    }

    public static void throwIfNotExist(String name) {
        stations().stream()
                .filter(it -> it.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 지하철역입니다"));
    }
}
