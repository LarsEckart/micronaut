package micro.messaging.twilio;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import micro.messaging.SMSGateway;

import javax.inject.Singleton;

@Singleton
class TwilioClient implements SMSGateway {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TwilioClient.class);

    private final RxHttpClient httpClient;
    private final String path;

    public TwilioClient(
            @Client(Config.TWILIO_URL) RxHttpClient httpClient,
            Config configuration) {
        this.httpClient = httpClient;
        path = configuration.path;
    }

    @Override
    public void send() {
        HttpResponse<ApiResponse> httpResponse = httpClient.toBlocking().exchange(
                HttpRequest.POST(path,
                        new TwilioMessage("+37258141113", "+37258821553", "hello"))
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .accept(MediaType.APPLICATION_HAL_JSON_TYPE),
                ApiResponse.class);
    }
}
