package subway;

import java.util.Scanner;
import subway.adapter.in.client.InputView;
import subway.adapter.in.client.OutputView;
import subway.adapter.in.client.SubwayClient;
import subway.application.SubwayApplication;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputView inputView = new InputView(scanner);
        OutputView outputView = new OutputView();
        SubwayClient subwayClient = new SubwayClient(new SubwayApplication(), inputView, outputView);
        subwayClient.run();
    }
}
