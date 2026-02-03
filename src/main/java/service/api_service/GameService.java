package service.api_service;


import core.RestClient;
import core.TextFilesUtil;
import service.api_object.OwnedGamesResponse;

import java.util.HashMap;
import java.util.Map;

public class GameService {
    private final RestClient client = new RestClient();
    private static final String GET_OWNED_GAMES_PATH = "/IPlayerService/GetOwnedGames/v0001/";

    public OwnedGamesResponse getAllGames() {
        Map<String, Object> params = new HashMap<>();
        params.put("key", TextFilesUtil.readFileName("steam_api_key.txt"));
        params.put("steamid", TextFilesUtil.readFileName("steam_id64.txt"));
        params.put("include_appinfo", 1);
        return client.get(GET_OWNED_GAMES_PATH, params, OwnedGamesResponse.class);
    }
}
