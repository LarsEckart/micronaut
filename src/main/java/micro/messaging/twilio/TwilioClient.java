package micro.messaging.twilio;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.http.uri.UriBuilder;
import micro.messaging.SMSGateway;

import java.util.Collections;
import java.util.Map;
import javax.inject.Singleton;

@Singleton
class TwilioClient implements SMSGateway {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TwilioClient.class);

    private final RxHttpClient httpClient;
    private final TwilioConfig configuration;

    public TwilioClient(
            @Client(TwilioConfig.API_URL) RxHttpClient httpClient,
            TwilioConfig configuration) {
        this.httpClient = httpClient;
        this.configuration = configuration;
    }

    @Override
    public Map send(String to, String text) {
        String uri = UriBuilder.of(configuration.path)
                .expand(Collections.singletonMap("accountSid", configuration.accountSid))
                .toString();

        Map<String, String> body = Map.of("To", "+" + to,
                "From", "+" + configuration.number,
                "Body", text);

        MutableHttpRequest<Map<String, String>> request = HttpRequest.POST(uri, body)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
                .accept(MediaType.APPLICATION_HAL_JSON_TYPE);

        try {
            return httpClient.toBlocking().retrieve(
                    request,
                    Argument.of(Map.class, String.class, String.class),
                    Argument.of(Map.class, String.class, String.class));
        } catch (HttpClientResponseException e) {
            Map responseBody = (Map) e.getResponse().body();
            log.error(responseBody.toString());
            return responseBody;
        }
    }
}
