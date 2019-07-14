package micro.messaging.twilio;

import io.micronaut.http.client.RxHttpClient;
import io.reactivex.Flowable;
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
    void send_request_to_twilio() throws IOException, InterruptedException {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody("{\"sid\":\"x\"}").addHeader("Content-type", "application/json"));

        URL url = new URL("http", "localhost", mockWebServer.getPort(), "");
        RxHttpClient client = RxHttpClient.create(url);
        TwilioConfig configuration = new TwilioConfig();
        configuration.accountSid = "anyAccountSid";
        configuration.authToken = "any";
        configuration.path = "/2010-04-01/Accounts/{accountSid}/Messages.json";
        configuration.number = "12345678";
        TwilioClient twilioClient = new TwilioClient(client, configuration);

        Flowable<Map> flowable = twilioClient.send("987654321", "hello");

        flowable.blockingFirst();

        RecordedRequest recordedRequest = mockWebServer.takeRequest(1, TimeUnit.SECONDS);
        assertThat(recordedRequest.getPath()).isEqualTo("/2010-04-01/Accounts/anyAccountSid/Messages.json");

        String actual = recordedRequest.getBody().readUtf8();
        assertThat(actual).contains("Body=hello");
        assertThat(actual).contains("From=%2B12345678");
        assertThat(actual).contains("To=%2B987654321");
    }
}