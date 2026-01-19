package api;

import org.testng.annotations.Test;
import service.api_object.PlayerAchievementsResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetPlayerAchievementsTest extends BaseApiTest{
    @Test
    public void shouldReturnPlayerAchievementsForGame() {
        PlayerAchievementsResponse playerAchievements = playerAchievementsService.getAllAchievements();
        assertThat(playerAchievements.getPlayerstats().getSuccess())
                .as("GetPlayerAchievements request should return success=true")
                .isTrue();
        assertThat(playerAchievements.getPlayerstats().getGameName())
                .as("Game name should be returned")
                .isNotBlank();
        assertThat(playerAchievements.getPlayerstats().getAchievements())
                .as("Achievements list should not be empty")
                .isNotEmpty();
        assertThat(playerAchievements.getPlayerstats().getAchievements())
                .allSatisfy(achievement -> {
                    assertThat(achievement.getApiName()).isNotBlank();
                    assertThat(achievement.getAchieved()).isBetween(0, 1);
                    assertThat(achievement.getUnlockTime()).isNotNegative();
                });
    }
}
