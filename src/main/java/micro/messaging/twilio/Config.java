package micro.messaging.twilio;

import io.micronaut.context.annotation.ConfigurationProperties;

import javax.validation.constraints.NotBlank;

@ConfigurationProperties(Config.PREFIX)
class Config {

    static final String PREFIX = "twilio";
    static final String TWILIO_URL = "https://api.twilio.com";

    @NotBlank
    String authToken;

    @NotBlank
    String accountSid;

    @NotBlank
    String path;

    @NotBlank
    String receiver;
}
