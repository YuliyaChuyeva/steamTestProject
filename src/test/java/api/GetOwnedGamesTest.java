package api;

import core.PropertiesReader;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class GetOwnedGamesTest {
    @Test
    public void compareNumberOfGames() {
        OwnedGamesResponse dto = given()
                .baseUri(PropertiesReader.getInstance().getUri())
                .queryParam("key", PropertiesReader.getInstance().getSteamApiKey())
                .queryParam("steamid", PropertiesReader.getInstance().getSteamId())
                .queryParam("include_appinfo", 1)
                .log().all()
                .when()
                .get("/IPlayerService/GetOwnedGames/v0001/")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(OwnedGamesResponse.class);
        assertEquals(1, dto.getResponse().getGameCount());
    }
}
