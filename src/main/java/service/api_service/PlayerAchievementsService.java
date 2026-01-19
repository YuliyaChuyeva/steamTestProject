package service.api_service;

import core.RestClient;
import core.TextFilesUtil;
import service.api_object.PlayerAchievementsResponse;

import java.util.HashMap;
import java.util.Map;

public class PlayerAchievementsService {
    private final RestClient client = new RestClient();
    private static final String GET_PLAYER_ACHIEVEMENT_PATH = "ISteamUserStats/GetPlayerAchievements/v0001/";

    public PlayerAchievementsResponse getAllAchievements() {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", 264200);
        params.put("key", TextFilesUtil.readFileName("steam_api_key.txt"));
        params.put("steamid", TextFilesUtil.readFileName("steam_id64.txt"));
        return client.get(GET_PLAYER_ACHIEVEMENT_PATH, params, PlayerAchievementsResponse.class);
    }
}
