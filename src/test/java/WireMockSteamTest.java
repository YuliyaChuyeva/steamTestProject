import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

public class WireMockSteamTest extends BaseApiTestWithWireMock {
    @BeforeMethod
    public void setupStubs() {
        wireMock.resetAll();
        wireMock.stubFor(get(urlPathEqualTo("/steam/get"))
                .withQueryParam("result", equalTo("success"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "status": "SUCCESS",
                                  "game": "No Man's Sky",
                                  "price": 27.99
                                }
                                """)));

        wireMock.stubFor(get(urlPathEqualTo("/steam/get"))
                .withQueryParam("result", equalTo("server_error"))
                .willReturn(aResponse()
                        .withStatus(500)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "status": "ERROR",
                                  "errorCode": "DATABASE_TIMEOUT",
                                  "message": "Request could not be processed because the database did not respond in time"
                                }
                                """)));

        wireMock.stubFor(get(urlPathEqualTo("/steam/get"))
                .withQueryParam("result", equalTo("conflict"))
                .willReturn(aResponse()
                        .withStatus(409)
                        .withHeader("Content-Type", "application/json")
                        .withBody("""
                                {
                                  "status": "CONFLICT",
                                  "message": "Game already in cart"
                                }
                                """)));
    }

    @Test
    public void shouldReturnSuccess200() {
        Response response = RestAssured.given()
                .baseUri(mockBaseUrl())
                .when()
                .get("/steam/get?result=success");
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("status")).isEqualTo("SUCCESS");
        wireMock.verify(getRequestedFor(urlPathEqualTo("/steam/get"))
                .withQueryParam("result", equalTo("success")));
    }

    @Test
    public void shouldReturnServerError500() {
        RestAssured.given()
                .baseUri(mockBaseUrl())
                .log().all()
                .when()
                .get("/steam/get?result=server_error")
                .then()
                .log().ifValidationFails()
                .statusCode(500);
    }

    @Test
    public void shouldReturnConflict409() {
        Response response = RestAssured.given()
                .baseUri(mockBaseUrl())
                .when()
                .get("/steam/get?result=conflict");
        assertThat(response.statusCode()).isEqualTo(409);
    }
}