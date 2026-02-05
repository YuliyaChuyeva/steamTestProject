package service.api_service;

import core.RestClient;
import core.util.TextFilesUtil;
import service.api_object.i_steam_user.GetPlayerSummariesResponse;

import java.util.HashMap;
import java.util.Map;

public class SteamUserService {
    private final RestClient client = new RestClient();
    private static final String GET_PLAYER_SUMMARIES = "ISteamUser/GetPlayerSummaries/v2/";

    public GetPlayerSummariesResponse getPlayerSummaries() {
        Map<String, Object> params = new HashMap<>();
        params.put("steamids", TextFilesUtil.readFileName("steam_id64.txt"));
        params.put("key", TextFilesUtil.readFileName("steam_api_key.txt"));
        return client.get(GET_PLAYER_SUMMARIES, params, GetPlayerSummariesResponse.class);
    }
}
