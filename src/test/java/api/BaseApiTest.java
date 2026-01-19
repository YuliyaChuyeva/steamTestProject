package api;

import service.api_service.GameService;
import service.api_service.NewsService;
import service.api_service.PlayerAchievementsService;

public abstract class BaseApiTest {
    protected GameService gameService= new GameService();
    PlayerAchievementsService playerAchievementsService =new PlayerAchievementsService();
    NewsService newsService = new NewsService();
}
