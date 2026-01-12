package api;

import org.testng.annotations.Test;
import service.api_object.OwnedGamesResponse;

import static org.testng.Assert.assertEquals;

public class GetOwnedGamesTest extends BaseApiTest {
    @Test
    public void compareNumberOfGames() {
        OwnedGamesResponse ownedGames = gameService.getAllGames();
        assertEquals(ownedGames.getResponse().getGameCount(), 1);
    }
}
