package api;

import api_expected_result.ExpectedGames;
import org.testng.annotations.Test;
import service.api_object.Game;
import service.api_object.OwnedGamesResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetOwnedGamesTest extends BaseApiTest {
    @Test
    public void compareNumberOfGames() {
        OwnedGamesResponse ownedGames = gameService.getAllGames();
        Game expectedGame = ExpectedGames.oneFingerDeathPunch();
        assertThat(ownedGames.getResponse().getGames())
                .as("Owned games list must contain expected game")
                .filteredOn(game -> expectedGame.getAppId().equals(game.getAppId()))
                .first()
                .usingRecursiveComparison()
                .comparingOnlyFields(
                        Game.Fields.appId,
                        Game.Fields.name
                )
                .isEqualTo(expectedGame);
    }
}
