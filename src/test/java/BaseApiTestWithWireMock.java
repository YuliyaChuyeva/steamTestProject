import com.github.tomakehurst.wiremock.WireMockServer;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class BaseApiTestWithWireMock {
    protected WireMockServer wireMock;

    @BeforeClass
    public void startWithMock() {
        wireMock = new WireMockServer(wireMockConfig().dynamicPort());
        wireMock.start();
    }

    @AfterClass(alwaysRun = true)
    public void stopWireMock() {
        if (wireMock != null) {
            wireMock.stop();
        }
    }

    protected String mockBaseUrl() {
        return "http://localhost:" + wireMock.port();
    }
}
