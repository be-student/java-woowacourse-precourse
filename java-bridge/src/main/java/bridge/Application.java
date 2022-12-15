package bridge;

import bridge.adapter.in.client.BridgeGameClient;
import bridge.adapter.in.client.InputView;
import bridge.adapter.in.client.OutputView;
import bridge.application.service.BridgeGame;

public class Application {

    public static void main(String[] args) {
        BridgeGameClient bridgeGameClient = getBridgeGameClient();
        bridgeGameClient.playBridgeGame();
    }

    private static BridgeGameClient getBridgeGameClient() {
        return new BridgeGameClient(new InputView(), new OutputView(), getBridgeGame());
    }

    private static BridgeGame getBridgeGame() {
        return new BridgeGame(new BridgeMaker(new BridgeRandomNumberGenerator()));
    }
}
