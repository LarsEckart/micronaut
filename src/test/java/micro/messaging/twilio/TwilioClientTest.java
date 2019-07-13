package micro.messaging.twilio;

import io.micronaut.http.client.RxHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class TwilioClientTest {

    @Test
    void name() throws IOException, InterruptedException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse().setBody("{}"));

        URL url = new URL("http", "localhost", mockWebServer.getPort(), "");
        RxHttpClient client = RxHttpClient.create(url);
        Config configuration = new Config();
        configuration.accountSid = "any";
        configuration.authToken = "any";
        configuration.path = "any";
        configuration.receiver = "12345678";
        TwilioClient twilioClient = new TwilioClient(client, configuration);

        twilioClient.send();

        RecordedRequest recordedRequest = mockWebServer.takeRequest(1, TimeUnit.SECONDS);
        String actual = recordedRequest.getBody().readUtf8();
        assertThat(actual).contains("body=hello");
        assertThat(actual).contains("from=%2B37258821553");
        assertThat(actual).contains("to=%2B12345678");
    }
}