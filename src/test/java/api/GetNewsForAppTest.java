package api;

import api_expected_result.ExpectedNews;
import org.testng.annotations.Test;
import service.api_object.AppNews;
import service.api_object.GetNewsForAppResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class GetNewsForAppTest extends BaseApiTest{
    @Test
    public void shouldReturnNewsForGame() {
        AppNews expected = ExpectedNews.oneFingerDeathPunch();
        int count = ExpectedNews.count();
        int maxLength = ExpectedNews.maxLength();
        GetNewsForAppResponse response =
                newsService.getNewsForApp(expected.getAppId(), count, maxLength);
        assertThat(response.getAppNews().getAppId())
                .isEqualTo(expected.getAppId());
        assertThat(response.getAppNews().getNewsItems())
                .isNotEmpty();
        assertThat(response.getAppNews().getNewsItems().size())
                .isLessThanOrEqualTo(count);
    }
}
