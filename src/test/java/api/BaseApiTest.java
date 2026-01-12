package api;

import core.PropertiesReader;
import core.RestClient;
import org.testng.annotations.BeforeMethod;
import service.api_service.GameService;

public abstract class BaseApiTest {
    protected GameService gameService;

    @BeforeMethod
    public void setUp() {
        String baseUri = PropertiesReader.getInstance().getUri();
        RestClient restClient = new RestClient(baseUri);
        gameService = new GameService(restClient);
    }
}
