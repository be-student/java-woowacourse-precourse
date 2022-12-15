package subway.adapter.in.client;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public GameCommand askRetry() {
        System.out.println();
        System.out.println("## 메인 화면");
        System.out.println("1. 경로 조회");
        System.out.println("Q. 종료");
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
        return GameCommand.fromCommand(scanner.nextLine());
    }

    public DistanceType askType() {
        System.out.println();
        System.out.println("## 경로 기준");
        System.out.println("1. 최단 거리");
        System.out.println("2. 최소 시간");
        System.out.println("B. 돌아가기");
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
        return DistanceType.fromCommand(scanner.nextLine());
    }

    public String askStartStation() {
        System.out.println();
        System.out.println("## 출발역을 입력하세요.");
        return scanner.nextLine();
    }

    public String askEndStation() {
        System.out.println();
        System.out.println("## 도착역을 입력하세요.");
        return scanner.nextLine();
    }
}
