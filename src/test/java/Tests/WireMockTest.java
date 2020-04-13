package Tests;

import Utils.Helpers.RESTHelper;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

//@Log4j
public class WireMockTest {

    private final String HOST = "localhost";
    private final int PORT = 8089;

    @Test
    public void TEST_WIREMOCK_01() throws IOException {
        WireMockServer wireMockServer = new WireMockServer(PORT);
        wireMockServer.start();
        configureFor(HOST, PORT);
        stubFor(get(urlEqualTo("/baeldung"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/xml")
                        .withBody("Welcome to Baeldung!")));

        HttpGet request = new HttpGet("http://" + HOST + ":" + PORT + "/baeldung");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpResponse httpResponse = httpClient.execute(request);
        String responseString = RESTHelper.convertResponseToString(httpResponse);
//        log.info(responseString);
    }
}
