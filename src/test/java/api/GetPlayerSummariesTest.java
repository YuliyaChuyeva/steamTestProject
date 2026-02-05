package api;

import core.util.TextFilesUtil;
import org.testng.annotations.Test;
import service.api_object.i_steam_user.GetPlayerSummariesResponse;
import service.api_object.i_steam_user.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPlayerSummariesTest extends BaseApiTest {
    private static final String STEAM_ID = TextFilesUtil.readFileName("steam_id64.txt");
    private static final String PERSONA_NAME = "dts.tech";
    private static final Integer PUBLIC_PROFILE = 3;

    @Test
    public void shouldReturnExpectedPlayerSummary() {
        Player expected = Player.builder()
                .steamId(STEAM_ID)
                .personaName(PERSONA_NAME)
                .communityVisibilityState(PUBLIC_PROFILE)
                .build();
        GetPlayerSummariesResponse response = steamUserService.getPlayerSummaries();
        Player actual = response.getResponse().getPlayers()
                .stream()
                .filter(p -> STEAM_ID.equals(p.getSteamId()))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError(
                                String.format(
                                        "Player with steamId=%s was not found in GetPlayerSummaries response",
                                        STEAM_ID
                                )
                        ));
        assertThat(actual)
                .as("Player summary should match expected values for steamId=%s", STEAM_ID)
                .usingRecursiveComparison()
                .comparingOnlyFields(
                        Player.Fields.steamId,
                        Player.Fields.personaName,
                        Player.Fields.communityVisibilityState
                )
                .isEqualTo(expected);
    }
}
