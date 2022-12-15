package subway.application;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import subway.application.port.in.MinimumDistanceCommand;
import subway.application.port.in.SubwayUseCase;
import subway.domain.Line;
import subway.domain.LineRepository;
import subway.domain.Station;
import subway.domain.StationRepository;
import subway.domain.SubwayResultDto;

public class SubwayApplication implements SubwayUseCase {


    private final WeightedMultigraph<String, DefaultWeightedEdge> timeGraph;
    private final WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph;

    public SubwayApplication() {
        timeGraph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        distanceGraph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        initVertex(timeGraph);
        initVertex(distanceGraph);
        initStationRepository();
        initLineRepository();
        initEdge();
    }

    private void initVertex(WeightedMultigraph<String, DefaultWeightedEdge> graph) {
        graph.addVertex("교대역");
        graph.addVertex("강남역");
        graph.addVertex("역삼역");
        graph.addVertex("남부터미널역");
        graph.addVertex("양재역");
        graph.addVertex("양재시민의숲역");
        graph.addVertex("매봉역");
    }

    private void initStationRepository() {
        StationRepository.addStation(new Station("교대역"));
        StationRepository.addStation(new Station("강남역"));
        StationRepository.addStation(new Station("역삼역"));
        StationRepository.addStation(new Station("남부터미널역"));
        StationRepository.addStation(new Station("양재역"));
        StationRepository.addStation(new Station("양재시민의숲역"));
        StationRepository.addStation(new Station("매봉역"));
    }

    private void initLineRepository() {
        LineRepository.addLine(new Line("교대역", "강남역", 3, 2));
        LineRepository.addLine(new Line("강남역", "역삼역", 3, 2));
        LineRepository.addLine(new Line("교대역", "남부터미널역", 2, 3));
        LineRepository.addLine(new Line("남부터미널역", "양재역", 5, 6));
        LineRepository.addLine(new Line("양재역", "매봉역", 1, 1));
        LineRepository.addLine(new Line("강남역", "양재역", 8, 2));
        LineRepository.addLine(new Line("양재역", "양재시민의숲역", 3, 10));
    }

    private void initEdge() {
        initEdgeByTime();
        initEdgeByDistance();
    }

    private void initEdgeByTime() {
        timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "강남역"), 3);
        timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "역삼역"), 3);
        timeGraph.setEdgeWeight(timeGraph.addEdge("교대역", "남부터미널역"), 2);
        timeGraph.setEdgeWeight(timeGraph.addEdge("남부터미널역", "양재역"), 5);
        timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "매봉역"), 1);
        timeGraph.setEdgeWeight(timeGraph.addEdge("강남역", "양재역"), 8);
        timeGraph.setEdgeWeight(timeGraph.addEdge("양재역", "양재시민의숲역"), 3);
    }

    private void initEdgeByDistance() {
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "강남역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "역삼역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("교대역", "남부터미널역"), 3);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("남부터미널역", "양재역"), 6);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("양재역", "매봉역"), 1);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("강남역", "양재역"), 2);
        distanceGraph.setEdgeWeight(distanceGraph.addEdge("양재역", "양재시민의숲역"), 10);
    }

    @Override
    public SubwayResultDto calculateResult(MinimumDistanceCommand minimumDistanceCommand) {
        validate(minimumDistanceCommand);
        if (minimumDistanceCommand.getFindByType().equals("1")) {
            return calculateByDistance(minimumDistanceCommand.getStartStation(),
                    minimumDistanceCommand.getFinishStation());
        }
        if (minimumDistanceCommand.getFindByType().equals("2")) {
            return calculateByTime(minimumDistanceCommand.getStartStation(),
                    minimumDistanceCommand.getFinishStation());
        }
        throw new IllegalArgumentException("입력 타입이 잘못되었습니다");
    }

    private void validate(MinimumDistanceCommand minimumDistanceCommand) {
        String startStation = minimumDistanceCommand.getStartStation();
        String endStation = minimumDistanceCommand.getFinishStation();
        if (startStation.equals(endStation)) {
            throw new IllegalArgumentException("출발역과 도착역이 동일합니다.");
        }
    }

    private SubwayResultDto calculateByDistance(String from, String end) {
        return calculateResult(from, end, distanceGraph);
    }


    private SubwayResultDto calculateByTime(String from, String end) {
        return calculateResult(from, end, timeGraph);
    }

    private SubwayResultDto calculateResult(String from, String end,
            WeightedMultigraph<String, DefaultWeightedEdge> distanceGraph) {
        StationRepository.throwIfNotExist(from);
        StationRepository.throwIfNotExist(end);
        var dijkstraShortestPath = new DijkstraShortestPath<>(distanceGraph);
        List<String> shortestPath = dijkstraShortestPath.getPath(from, end).getVertexList();
        List<Line> lines = new ArrayList<>();
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            lines.add(LineRepository.findWithFromAndTo(shortestPath.get(i), shortestPath.get(i + 1)));
        }
        return new SubwayResultDto(shortestPath, lines);
    }
}
