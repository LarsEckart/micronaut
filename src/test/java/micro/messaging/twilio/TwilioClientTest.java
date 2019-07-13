package micro.messaging.twilio;

import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class TwilioClientTest {

    @Test
    void name() throws IOException, InterruptedException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("{\"sid\":\"x\"}").addHeader("Content-type", "application/json"));

        URL url = new URL("http", "localhost", mockWebServer.getPort(), "");
        RxHttpClient client = RxHttpClient.create(url);
        Config configuration = new Config();
        configuration.accountSid = "anyAccountSid";
        configuration.authToken = "any";
        configuration.path = "/2010-04-01/Accounts/{accountSid}/Messages.json";
        configuration.receiver = "12345678";
        TwilioClient twilioClient = new TwilioClient(client, configuration);

        Map send = twilioClient.send();

        RecordedRequest recordedRequest = mockWebServer.takeRequest(1, TimeUnit.SECONDS);
        assertThat(recordedRequest.getPath()).isEqualTo("/2010-04-01/Accounts/anyAccountSid/Messages.json");

        String actual = recordedRequest.getBody().readUtf8();
        assertThat(actual).contains("body=hello");
        assertThat(actual).contains("from=%2B37258821553");
        assertThat(actual).contains("to=%2B12345678");
    }
}