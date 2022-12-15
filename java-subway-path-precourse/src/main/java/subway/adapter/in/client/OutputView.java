package subway.adapter.in.client;

import subway.domain.Line;
import subway.domain.SubwayResultDto;

public class OutputView {

    public void printError(String errorMessage) {
        System.out.println();
        System.out.println("[ERROR]" + errorMessage);
    }

    public void printResult(SubwayResultDto subwayResultDto) {
        System.out.println("## 조회 결과");
        printInfo("---");
        printDistance(subwayResultDto);
        printTime(subwayResultDto);
        printInfo("---");
        printStations(subwayResultDto);
        System.out.println();
    }

    private void printInfo(String message) {
        System.out.println("[INFO] " + message);
    }

    private void printDistance(SubwayResultDto subwayResultDto) {
        int result = subwayResultDto.getLines()
                .stream()
                .map(Line::getDistance)
                .reduce(0, Integer::sum);
        printInfo("총 거리: " + result + "km");
    }

    private void printTime(SubwayResultDto subwayResultDto) {
        int result = subwayResultDto.getLines()
                .stream()
                .map(Line::getTime)
                .reduce(0, Integer::sum);
        printInfo("총 소요 시간: " + result + "분");
    }

    private void printStations(SubwayResultDto subwayResultDto) {
        subwayResultDto.getStations()
                .forEach(this::printInfo);
    }

}
