package subway.application.port.in;

public class MinimumDistanceCommand {
    private final String findByType;
    private final String startStation;
    private final String finishStation;

    public MinimumDistanceCommand(String findByType, String startStation, String finishStation) {
        this.finishStation=finishStation;
        this.findByType=findByType;
        this.startStation=startStation;
    }

    public String getFindByType() {
        return findByType;
    }

    public String getFinishStation() {
        return finishStation;
    }

    public String getStartStation() {
        return startStation;
    }
}
