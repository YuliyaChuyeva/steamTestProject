package api;

import org.testng.annotations.Test;
import service.api_object.SteamLevelResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetSteamLevelTest extends BaseApiTest {
    @Test
    public void shouldReturnSteamLevelForPlayer() {
        SteamLevelResponse response = gameService.getSteamLevel();
        assertThat(response.getResponse().getPlayerLevel())
                .as("Steam level for player  should be returned and be non-negative")
                .isNotNull()
                .isGreaterThanOrEqualTo(0);
    }
}
