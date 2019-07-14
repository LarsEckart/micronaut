package micro.messaging.twilio;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(TwilioConfig.PREFIX)
class TwilioConfig {

    static final String PREFIX = "twilio";
    static final String API_URL = "https://api.twilio.com";

    @NotBlank
    String authToken;

    @NotBlank
    String accountSid;

    @NotBlank
    String path;

    @NotBlank
    String number;
}
