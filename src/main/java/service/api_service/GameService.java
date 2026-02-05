package service.api_service;


import core.RestClient;
import core.util.TextFilesUtil;
import service.api_object.SteamLevelResponse;
import service.api_object.i_player_service.OwnedGamesResponse;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    private final RestClient client = new RestClient();
    private static final String GET_OWNED_GAMES_PATH = "/IPlayerService/GetOwnedGames/v0001/";
    private static final String GET_STEAM_LEVEL = "IPlayerService/GetSteamLevel/v1";

    public OwnedGamesResponse getAllGames() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", TextFilesUtil.readFileName("steam_api_key.txt"));
        params.put("steamid", TextFilesUtil.readFileName("steam_id64.txt"));
        params.put("include_appinfo", 1);
        return client.get(GET_OWNED_GAMES_PATH, params, OwnedGamesResponse.class);
    }

    public SteamLevelResponse getSteamLevel() {
        Map<String, Object> params = new HashMap<>();
        params.put("steamid", TextFilesUtil.readFileName("steam_id64.txt"));
        params.put("key", TextFilesUtil.readFileName("steam_api_key.txt"));
        return client.get(GET_STEAM_LEVEL, params, SteamLevelResponse.class);
    }
}
