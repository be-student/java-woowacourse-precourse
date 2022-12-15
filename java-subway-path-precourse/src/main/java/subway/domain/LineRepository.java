package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {

    private static final List<Line> lines = new ArrayList<>();

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        lines.clear();
    }

    public static Line findWithFromAndTo(String from, String to) {
        return lines.stream().filter(it -> it.getFrom().getName().equals(from) || it.getFrom().getName().equals(to))
                .filter(it -> it.getTo().getName().equals(from) || it.getFrom().getName().equals(to))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 간선입니다"));
    }
}
