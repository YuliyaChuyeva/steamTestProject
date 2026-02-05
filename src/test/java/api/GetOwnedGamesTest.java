package api;

import org.testng.annotations.Test;
import service.api_object.i_player_service.Game;
import service.api_object.i_player_service.OwnedGamesResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetOwnedGamesTest extends BaseApiTest {
    private static final String NAME_OF_GAME = "One Finger Death Punch";
    private static final Integer APP_ID = 264200;

    @Test
    public void compareNumberOfGames() {
        Game expectedGame = Game.builder()
                .name(NAME_OF_GAME)
                .appId(APP_ID)
                .build();
        OwnedGamesResponse ownedGames = gameService.getAllGames();
        assertThat(ownedGames.getResponse().getGames())
                .as("Owned games list should contain %s (appid=%s)", NAME_OF_GAME, APP_ID)
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
