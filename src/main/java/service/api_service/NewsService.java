package service.api_service;

import core.RestClient;
import service.api_object.GetNewsForAppResponse;

import java.util.HashMap;
import java.util.Map;

public class NewsService {
    private final RestClient client = new RestClient();
    private static final String GET_NEWS_APP_PATH = "/ISteamNews/GetNewsForApp/v0002/";

    public GetNewsForAppResponse getNewsForApp(int appId, int count, int maxLength) {
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appId);
        params.put("count", count);
        params.put("maxlength", maxLength);
        return client.get(GET_NEWS_APP_PATH, params, GetNewsForAppResponse.class);
    }
}
