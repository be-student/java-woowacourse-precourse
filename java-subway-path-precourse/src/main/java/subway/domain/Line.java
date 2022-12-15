package subway.domain;

public class Line {

    private final Station from;
    private final Station to;
    private final int time;
    private final int distance;
    private String name;

    public Line(String from, String to, int time, int distance) {
        this.time = time;
        this.distance = distance;
        this.from = new Station(from);
        this.to = new Station(to);
    }

    public Station getFrom() {
        return from;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public Station getTo() {
        return to;
    }

    public String getName() {
        return name;
    }
}
