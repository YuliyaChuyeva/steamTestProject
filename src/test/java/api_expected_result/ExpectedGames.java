package api_expected_result;

import service.api_object.Game;

public class ExpectedGames {

    public static Game oneFingerDeathPunch() {
        Game game = new Game();
        game.setName("One Finger Death Punch");
        game.setAppId(264200);
        return game;
    }
}
