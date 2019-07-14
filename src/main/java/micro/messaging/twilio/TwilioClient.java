package micro.messaging.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
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
    public Map send() {
        Twilio.init(configuration.accountSid, configuration.authToken);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+" + configuration.receiver),
                new com.twilio.type.PhoneNumber("+37258821553"),
                "hello")
                .create();
        log.info("sms sid: " + message.getSid());
        return Collections.singletonMap("sid", message.getSid());

        /*
        String uri = UriBuilder.of(configuration.path)
                .expand(Collections.singletonMap("accountSid", configuration.accountSid))
                .toString();

        TwilioMessage body = new TwilioMessage("+" + configuration.receiver, "+37258821553", "hello");

        MutableHttpRequest<TwilioMessage> request = HttpRequest.POST(uri, body)
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
         */

    }
}
