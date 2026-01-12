package core;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestClient {
    private final String baseUri;

    public RestClient(String baseUri) {
        this.baseUri = baseUri;
    }

    private RequestSpecification baseRequest() {
        return RestAssured.given()
                .baseUri(baseUri)
                .log().all();
    }

    public <T> T get(String path, Map<String, ?> queryParams, Class<T> responseClass) {
        return baseRequest()
                .queryParams(queryParams)
                .when()
                .get(path)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract()
                .as(responseClass);
    }
}
