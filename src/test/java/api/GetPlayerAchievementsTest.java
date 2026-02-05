package api;

import org.testng.annotations.Test;
import service.api_object.i_steam_user_stats.Achievement;
import service.api_object.i_steam_user_stats.PlayerAchievementsResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPlayerAchievementsTest extends BaseApiTest {
    private static final String ACHIEVEMENT_API_NAME = "twobuttons";

    @Test
    public void shouldContainExpectedAchievement() {
        Achievement expected = Achievement.builder()
                .apiName(ACHIEVEMENT_API_NAME)
                .achieved(0)
                .unlockTime(0)
                .build();
        PlayerAchievementsResponse response = playerAchievementsService.getAllAchievements();
        Achievement actual = response.getPlayerstats().getAchievements()
                .stream()
                .filter(a -> expected.getApiName().equals(a.getApiName()))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError(
                                String.format(
                                        "Achievement with apiName=%s was not found in GetPlayerAchievements response",
                                        ACHIEVEMENT_API_NAME
                                )
                        )
                );
        assertThat(actual)
                .as("Achievement should match expected values for apiName=%s", expected.getApiName())
                .usingRecursiveComparison()
                .comparingOnlyFields(
                        Achievement.Fields.apiName,
                        Achievement.Fields.achieved,
                        Achievement.Fields.unlockTime
                )
                .isEqualTo(expected);
    }
}
