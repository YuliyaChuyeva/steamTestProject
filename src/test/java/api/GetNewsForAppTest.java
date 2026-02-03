package api;

import org.testng.annotations.Test;
import service.api_object.AppNews;
import service.api_object.GetNewsForAppResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetNewsForAppTest extends BaseApiTest {
    private static final int APP_ID = 264200;
    private static final int REQUEST_COUNT = 3;

    @Test
    public void shouldReturnNewsForGame() {
        GetNewsForAppResponse response = newsService.getNewsForApp(APP_ID, REQUEST_COUNT);
        AppNews actual = response.getAppNews();
        assertThat(actual.getAppId())
                .as("App id in response should be %s", APP_ID)
                .isEqualTo(APP_ID);
        assertThat(actual.getNewsItems())
                .as("News items list should not be empty and should contain no more than %s items",
                        REQUEST_COUNT)
                .isNotEmpty()
                .hasSizeLessThanOrEqualTo(REQUEST_COUNT);
    }
}